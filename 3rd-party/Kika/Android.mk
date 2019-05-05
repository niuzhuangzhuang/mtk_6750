LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE := Kika
LOCAL_MODULE_TAGS := optional
LOCAL_SRC_FILES := Kika_KA_JC_DK_5.7.0.1_3160_20170710_c8e79ac.apk 
LOCAL_MODULE_CLASS := APPS
LOCAL_MODULE_SUFFIX := $(COMMON_ANDROID_PACKAGE_SUFFIX)
LOCAL_CERTIFICATE := PRESIGNED
LOCAL_MODULE_PATH := $(TARGET_OUT)/app
LOCAL_PREBUILT_JNI_LIBS := \
        lib/libAndroidSpeex.so \
        lib/libcrashlytics.so \
        lib/libcrashlytics-envelope.so \
        lib/libgifflen.so \
        lib/libjni_latinime_kika.so \
        lib/libjni_pinyinime.so \
        lib/librsjni.so \
        lib/libRSSupport.so \
        lib/libun7z.so \
        lib/libunwind-crashlytics.so
include $(BUILD_PREBUILT)

