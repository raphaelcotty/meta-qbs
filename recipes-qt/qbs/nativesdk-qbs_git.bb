SUMMARY = "Qbs: Modern build tool for software projects"
DESCRIPTION = "Qbs is a tool that helps simplify the build process for developing projects across multiple platforms.\
Qbs can be used for any software project, regardless of programming language, toolkit, or libraries used."

LICENSE = "LGPLv3"
LIC_FILES_CHKSUM = "file://LICENSE.LGPLv3;md5=466a5dbc4996e12165deb9b21185e2ad"

DEPENDS = "nativesdk-qtbase"

SRCREV = "001bf31623c02ba8249dd066777d014d546eb7f9"

SRC_URI = "gitsm://code.qt.io/qbs/qbs.git;branch=master;protocol=http"

S = "${WORKDIR}/git"

SRC_URI[md5sum] = "4b73c8538cc3bbee6bc2b1514117d882"
SRC_URI[sha256sum] = "23fb37bfbe775907a2593f716b17151f25995ec37cd5bedabaf266781078b404"

QMAKE_PROFILES = "${S}/qbs.pro"
EXTRA_QMAKEVARS_PRE = "QBS_INSTALL_PREFIX=${STAGING_DIR_NATIVE}/usr CONFIG+=nomake_tests CONFIG-=cross_compile"

inherit nativesdk qmake5

do_install_class-nativesdk() {
    # Fix install paths for all
    find . -name "Makefile*" | xargs -r sed -i "s,(INSTALL_ROOT)${STAGING_DIR_HOST},(INSTALL_ROOT),g"

    oe_runmake install INSTALL_ROOT=${D}
}

