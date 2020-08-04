package com.elly.twowalls.tools.Assets;

import com.badlogic.gdx.assets.AssetManager;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;


public class AssetAnalyser {

    public static void parse(AssetManager manager, Object o) {
        Class<?> oClass = o.getClass();
        Field[] fields = oClass.getFields();
        for (Field f : fields) {
            if (f.isAnnotationPresent(Asset.class)) {
                try {
                    Asset asset = f.getAnnotation(Asset.class);
                    manager.load(asset.folder() + f.get(o), asset.type());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void parseAll(AssetManager manager, Object... assets){
        for (Object o: assets)
            parse(manager, o);
    }
}


@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@interface Asset {
    Class type();

    String folder();
}
