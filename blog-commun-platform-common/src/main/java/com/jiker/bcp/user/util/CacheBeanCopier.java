package com.jiker.bcp.user.util;

import org.springframework.cglib.beans.BeanCopier;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jiker.luo
 * @date 2020/4/1
 */
public class CacheBeanCopier {

    static final Map<String, BeanCopier> BEAN_COPIERS = new HashMap<>();

    public static void copy(Object srcObj,Object destObj){
        if (srcObj == null){
            destObj = null;
            return;
        }
        String key = genKey(srcObj.getClass(),destObj.getClass());
        BeanCopier copier = null;
        if (!BEAN_COPIERS.containsKey(key)){
            copier = BeanCopier.create(srcObj.getClass(),destObj.getClass(),false);
            BEAN_COPIERS.put(key,copier);
        } else {
            copier = BEAN_COPIERS.get(key);
        }
        copier.copy(srcObj,destObj,null);
    }

    private static String genKey(Class<?> srcClass,Class<?> destClass){
        return srcClass.getName() + destClass.getName();
    }

}
