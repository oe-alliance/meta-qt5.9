require qt5.inc
require qt5-git.inc

DEPENDS += "qtbase qtdeclarative wayland wayland-native qtwayland-native"
DEPENDS:append:class-target = " libxkbcommon"

LICENSE = "GFDL-1.3 & BSD-3-Clause & ( GPL-3.0-only & The-Qt-Company-GPL-Exception-1.0 | The-Qt-Company-Commercial ) & ( GPL-2.0-or-later | LGPL-3.0-only | The-Qt-Company-Commercial )"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPL3;md5=e6a600fd5e1d9cbde2d983680233ad02 \
    file://LICENSE.GPL2;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSE.GPL3-EXCEPT;md5=763d8c535a234d9a3fb682c7ecb6c073 \
    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e \
"

PACKAGECONFIG ?= " \
    wayland-client \
    wayland-server \
    wayland-egl \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'xcomposite-egl xcomposite-glx', '', d)} \
"
PACKAGECONFIG:class-native ?= ""
PACKAGECONFIG:class-nativesdk ?= ""
QMAKE_PROFILES:class-native = "${S}/src/qtwaylandscanner"
QMAKE_PROFILES:class-nativesdk = "${S}/src/qtwaylandscanner"
B:class-native = "${SEPB}/src/qtwaylandscanner"
B:class-nativesdk = "${SEPB}/src/qtwaylandscanner"

PACKAGECONFIG[wayland-client] = "-feature-wayland-client,-no-feature-wayland-client"
PACKAGECONFIG[wayland-server] = "-feature-wayland-server,-no-feature-wayland-server"
PACKAGECONFIG[xcomposite-egl] = "-feature-xcomposite-egl,-no-feature-xcomposite-egl,libxcomposite"
PACKAGECONFIG[xcomposite-glx] = "-feature-xcomposite-glx,-no-feature-xcomposite-glx,virtual/mesa"
PACKAGECONFIG[wayland-egl] = "-feature-wayland-egl,-no-feature-wayland-egl,virtual/egl"
PACKAGECONFIG[wayland-brcm] = "-feature-wayland-brcm,-no-feature-wayland-brcm,virtual/egl"
PACKAGECONFIG[drm-egl-server] = "-feature-drm-egl-server,-no-feature-drm-egl-server,libdrm virtual/egl"
PACKAGECONFIG[libhybris-egl-server] = "-feature-libhybris-egl-server,-no-feature-libhybris-egl-server,libhybris"

EXTRA_QMAKEVARS_CONFIGURE += "${PACKAGECONFIG_CONFARGS}"

SRCREV = "79ec94d0efbeabe485275e5c79c828aa64641afa"

# Patches from https://github.com/meta-qt5/qtwayland/commits/b5.9
# 5.9.meta-qt5.8
# From https://bugreports.qt.io/browse/QTBUG-57767
SRC_URI += " \
    file://0001-fix-build-without-xkbcommon-evdev.patch \
"

BBCLASSEXTEND =+ "native nativesdk"

# The same issue as in qtbase:
# http://errors.yoctoproject.org/Errors/Details/152641/
LDFLAGS:append:x86 = "${@bb.utils.contains('DISTRO_FEATURES', 'ld-is-gold', ' -fuse-ld=bfd ', '', d)}"
