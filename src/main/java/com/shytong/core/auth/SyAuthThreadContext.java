package com.shytong.core.auth;

import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sytong
 * @Package com.shytong.core.auth
 * @Description:
 * @date 2018-04-0315:22
 */
public class SyAuthThreadContext {
    public static final String SUBJECT_CONTEXT_KEY= SyAuthThreadContext.class.getName() + "_SUBJECT_CONTEXT_KEY";
    public static final String SECURITY_MANAGER_KEY = SyAuthThreadContext.class.getName() + "_SECURITY_MANAGER_KEY";
    public static final String SUBJECT_KEY = SyAuthThreadContext.class.getName() + "_SUBJECT_KEY";

    private static final ThreadLocal<Map<Object, Object>> resources = new SyAuthThreadContext.InheritableThreadLocalMap<Map<Object, Object>>();

    /**
     * Default no-argument constructor.
     */
    protected SyAuthThreadContext() {
    }

    /**
     * Returns the ThreadLocal Map. This Map is used internally to bind objects
     * to the current thread by storing each object under a unique key.
     *
     * @return the map of bound resources
     */
    public static Map<Object, Object> getResources() {
        if (resources.get() == null){
            return Collections.emptyMap();
        } else {
            return new HashMap<Object, Object>(resources.get());
        }
    }


    public static void setResources(Map<Object, Object> newResources) {
        if (CollectionUtils.isEmpty(newResources)) {
            return;
        }
        ensureResourcesInitialized();
        resources.get().clear();
        resources.get().putAll(newResources);
    }


    private static Object getValue(Object key) {
        Map<Object, Object> perThreadResources = resources.get();
        return perThreadResources != null ? perThreadResources.get(key) : null;
    }

    private static void ensureResourcesInitialized(){
        if (resources.get() == null){
            resources.set(new HashMap<Object, Object>());
        }
    }


    public static Object get(Object key) {

        Object value = getValue(key);

        return value;
    }


    public static void put(Object key, Object value) {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }

        if (value == null) {
            remove(key);
            return;
        }

        ensureResourcesInitialized();
        resources.get().put(key, value);


    }

    public static Object remove(Object key) {
        Map<Object, Object> perThreadResources = resources.get();
        Object value = perThreadResources != null ? perThreadResources.remove(key) : null;



        return value;
    }


    public static void remove() {
        resources.remove();
    }
    public static SubjectContext getSubjectContext() {
        return (SubjectContext) get(SUBJECT_CONTEXT_KEY);
    }

    public static void bind(SubjectContext subjectContext) {
        if (subjectContext != null) {
            put(SUBJECT_CONTEXT_KEY, subjectContext);
        }
    }
    public static SySecurityManager getSySecurityManager(String code) {


        return (SySecurityManager) get(SECURITY_MANAGER_KEY+code);
    }

    public static void bind(String code, SySecurityManager SySecurityManager) {
        if (SySecurityManager != null) {
            put(SECURITY_MANAGER_KEY+code, SySecurityManager);
        }
    }
    public static SubjectContext unbindSubjectContext () {
        return (SubjectContext) remove(SUBJECT_CONTEXT_KEY);
    }

    public static SySecurityManager unbindSySecurityManager(String code) {
        return (SySecurityManager) remove(SECURITY_MANAGER_KEY+code);
    }

    public static Subject getSubject(String sysTemCode) {


        return (Subject) get(sysTemCode);
    }
    public static void bind(String code, Subject subject) {
        if (subject != null) {
            put(code, subject);
        }
    }


    public static Subject getSubject() {
        return (Subject) get(SUBJECT_KEY);
    }


    public static void bind(Subject subject) {
        if (subject != null) {
            put(SUBJECT_KEY, subject);
        }
    }

    public static Subject unbindSubject() {
        return (Subject) remove(SUBJECT_KEY);
    }
    public static Subject unbindSubject(String code) {
        return (Subject) remove(code);
    }

    private static final class InheritableThreadLocalMap<T extends Map<Object, Object>> extends InheritableThreadLocal<Map<Object, Object>> {

        @Override
        @SuppressWarnings({"unchecked"})
        protected Map<Object, Object> childValue(Map<Object, Object> parentValue) {
            if (parentValue != null) {
                return (Map<Object, Object>) ((HashMap<Object, Object>) parentValue).clone();
            } else {
                return null;
            }
        }
    }

}
