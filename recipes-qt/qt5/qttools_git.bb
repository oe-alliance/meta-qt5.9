require qt5.inc
require qt5-git.inc
require qt5-ptest.inc

HOMEPAGE = "http://www.qt.io"
LICENSE = "GFDL-1.3 & BSD-3-Clause & ( GPL-3.0-only & The-Qt-Company-GPL-Exception-1.0 | The-Qt-Company-Commercial ) & ( GPL-2.0-or-later | LGPL-3.0-only | The-Qt-Company-Commercial )"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPL3;md5=e6a600fd5e1d9cbde2d983680233ad02 \
    file://LICENSE.GPL2;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSE.GPL3-EXCEPT;md5=763d8c535a234d9a3fb682c7ecb6c073 \
    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e \
"

DEPENDS += "qtbase qtdeclarative qtxmlpatterns"

# Patches from https://github.com/meta-qt5/qttools/commits/b5.9
# 5.9.meta-qt5.8
SRC_URI += " \
    file://0001-add-noqtwebkit-configuration.patch \
    file://0002-linguist-tools-cmake-allow-overriding-the-location-f.patch \
"

FILES:${PN}-tools += "${datadir}${QT_DIR_NAME}/phrasebooks"
FILES:${PN}-examples = "${datadir}${QT_DIR_NAME}/examples"

PACKAGECONFIG ??= ""
PACKAGECONFIG[qtwebkit] = ",,qtwebkit"

EXTRA_QMAKEVARS_PRE += "${@bb.utils.contains('PACKAGECONFIG', 'qtwebkit', '', 'CONFIG+=noqtwebkit', d)}"

SRCREV = "cb4fb7bdd59bb57324893cf1b226f380d03b574f"

BBCLASSEXTEND = "native nativesdk"

do_install_ptest() {
    mkdir -p ${D}${PTEST_PATH}
    t=${D}${PTEST_PATH}
    cp ${B}/tests/auto/qtdiag/tst_tdiag $t
    cp ${B}/tests/auto/qtattributionsscanner/tst_qtattributionsscanner $t
}
