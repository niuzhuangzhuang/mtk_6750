LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE := AdvanStore
LOCAL_MODULE_TAGS := optional
LOCAL_SRC_FILES := Advan_Store.apk
LOCAL_MODULE_CLASS := APPS
LOCAL_MODULE_SUFFIX := $(COMMON_ANDROID_PACKAGE_SUFFIX)
LOCAL_CERTIFICATE := PRESIGNED
LOCAL_MODULE_PATH := $(TARGET_OUT)/app
LOCAL_PREBUILT_JNI_LIBS := \
        lib/armeabi/libbspatch.so \
        lib/armeabi/libtpnsSecurity.so \
        lib/armeabi/libtpnsWatchdog.so
include $(BUILD_PREBUILT)

