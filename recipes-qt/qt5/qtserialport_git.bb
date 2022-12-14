require qt5.inc
require qt5-git.inc

# text of LGPL_EXCEPTION.txt and LICENSE.FDL is slightly different than what
# other qt* components use :/
LICENSE = "GFDL-1.3 & BSD-3-Clause & (LGPL-2.1-only & The-Qt-Company-Qt-LGPL-Exception-1.1 | LGPL-3.0-only) | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPL3;md5=e6a600fd5e1d9cbde2d983680233ad02 \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSE.GPL3-EXCEPT;md5=763d8c535a234d9a3fb682c7ecb6c073 \
    file://LGPL_EXCEPTION.txt;md5=bb426f3367c4805d1e12fad05bd0b750 \
    file://LICENSE.FDL;md5=3801d7932fdc07fd9efe89f9854a6caa \
    file://LICENSE.GPL2;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
"

DEPENDS += "qtbase"

SRCREV = "a5b4f0511006bfa4999beb0df094e11d25a4e6eb"
