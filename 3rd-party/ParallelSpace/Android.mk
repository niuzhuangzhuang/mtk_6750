#
# Copyright (C) 2009 The Android Open Source Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE := ParallelSpace
LOCAL_MODULE_TAGS := optional
LOCAL_SRC_FILES := LBEParallelSpace_360OS_20170606.apk
LOCAL_MODULE_CLASS := APPS
LOCAL_MODULE_SUFFIX := $(COMMON_ANDROID_PACKAGE_SUFFIX)
LOCAL_CERTIFICATE := PRESIGNED
LOCAL_MODULE_PATH := $(TARGET_OUT)/priv-app
LOCAL_PREBUILT_JNI_LIBS := \
    lib/armeabi/libdaclient.so \
    lib/armeabi/libdaclient_64.so \
    lib/armeabi/libdaclient_x86.so \
    lib/armeabi/libdadebugger_ics.so \
    lib/armeabi/libdaunwind_64.so \
    lib/armeabi/libuninstmon.so \
    lib/armeabi/libdaclient.so \
    lib/armeabi/libdadebugger_64.so \
    lib/armeabi/libdadebugger.so
include $(BUILD_PREBUILT)

