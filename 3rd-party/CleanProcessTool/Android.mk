LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
LOCAL_MODULE_TAGS := optional
LOCAL_MODULE := CleanProcessTool
LOCAL_SRC_FILES := CleanProcessTool.apk
LOCAL_MODULE_CLASS := APPS
LOCAL_MODULE_SUFFIX := $(COMMON_ANDROID_PACKAGE_SUFFIX)
LOCAL_CERTIFICATE := platform
LOCAL_DEX_PREOPT := false
LOCAL_MODULE_PATH := $(TARGET_OUT)/priv-app
include $(BUILD_PREBUILT)