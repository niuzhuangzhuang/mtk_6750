LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
LOCAL_MODULE := OperaMini
LOCAL_MODULE_TAGS := optional
LOCAL_SRC_FILES := Opera_Mini_Native.apk
LOCAL_OVERRIDES_PACKAGES := MtkBrowser
LOCAL_MODULE_CLASS := APPS
LOCAL_MODULE_SUFFIX := $(COMMON_ANDROID_PACKAGE_SUFFIX)
LOCAL_CERTIFICATE := PRESIGNED
LOCAL_MODULE_PATH := $(TARGET_OUT)/app
LOCAL_PREBUILT_JNI_LIBS := \
        lib/armeabi/libom.so \
        lib/armeabi/libtix86.so \
        lib/armeabi/libutil.so
include $(BUILD_PREBUILT)

