# Copyright (C) 2014 O.S. Systems Software LTDA.

SUMMARY = "Host packages for the Qt5 standalone SDK or external toolchain"
LICENSE = "MIT"

inherit packagegroup nativesdk

PACKAGEGROUP_DISABLE_COMPLEMENTARY = "1"

RDEPENDS:${PN} += " \
    nativesdk-packagegroup-sdk-host \
    nativesdk-qttools-tools \
    nativesdk-qtbase-tools \
"
