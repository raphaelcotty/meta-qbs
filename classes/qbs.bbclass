B = "${WORKDIR}/build"

OE_QBS_QBS = "${STAGING_BINDIR_NATIVE}/qbs"
OE_QBS_CONFIG = "${STAGING_BINDIR_NATIVE}/qbs-config"
OE_QBS_SETUP_TOOLCHAINS = "${STAGING_BINDIR_NATIVE}/qbs-setup-toolchains"
OE_QBS_SETUP_QT = "${STAGING_BINDIR_NATIVE}/qbs-setup-qt"
OE_QBS_SETTINGS_DIR = "${WORKDIR}/qbs"
OE_QBS_QT_VERSION = "qt5"

DEPENDS = "qbs-native"

OE_QBS_BUILD_VARIANT = "${@oe.utils.conditional( "DEBUG_BUILD", "1", "debug", "release", d )}"

qbs_do_compile()  {
    cd ${B}
    ${OE_QBS_QBS} build --command-echo-mode command-line --no-install qbs.installPrefix:/usr --settings-dir ${OE_QBS_SETTINGS_DIR} --build-directory ${B} --file ${S} qbs.installRoot:${D} qbs.buildVariant:${OE_QBS_BUILD_VARIANT} profile:${OE_QBS_QT_VERSION}
}

do_compile() {
    qbs_do_compile
}

qbs_do_install()  {
    cd ${B}
    ${OE_QBS_QBS} install --no-build qbs.installPrefix:/usr --settings-dir ${OE_QBS_SETTINGS_DIR} --build-directory ${B} --file ${S} --clean-install-root qbs.installRoot:${D} qbs.buildVariant:${OE_QBS_BUILD_VARIANT} profile:${OE_QBS_QT_VERSION}
}

python __anonymous () {
    cxx_split = d.getVar("CXX", True).split()
    d.setVar("CXX_PATH", cxx_split[0])
}

do_install() {
    qbs_do_install
}

do_generate_profile() {
    export QT_CONF_PATH=${WORKDIR}/qt.conf
    cat > ${WORKDIR}/qt.conf <<EOF
[Paths]
Data = ${STAGING_LIBDIR}/${OE_QBS_QT_VERSION}
HostData = ${STAGING_LIBDIR}/${OE_QBS_QT_VERSION}
Headers = ${STAGING_INCDIR}/${OE_QBS_QT_VERSION}
Libraries = ${STAGING_LIBDIR}
Binaries = ${STAGING_BINDIR_NATIVE}
HostBinaries = ${STAGING_BINDIR_NATIVE}/${OE_QBS_QT_VERSION}
Plugins = ${STAGING_LIBDIR}/${OE_QBS_QT_VERSION}/plugins/
EOF

${OE_QBS_SETUP_TOOLCHAINS} --settings-dir ${OE_QBS_SETTINGS_DIR} --type gcc ${CXX_PATH} ${TARGET_ARCH}-gcc

${OE_QBS_CONFIG} --settings-dir ${OE_QBS_SETTINGS_DIR} profiles.${TARGET_ARCH}-gcc.cpp.sysroot ${STAGING_DIR_HOST}
${OE_QBS_CONFIG} --settings-dir ${OE_QBS_SETTINGS_DIR} profiles.${TARGET_ARCH}-gcc.cpp.linkerFlags --hash-style=gnu

${OE_QBS_SETUP_QT} --settings-dir ${OE_QBS_SETTINGS_DIR} ${STAGING_BINDIR_NATIVE}/qmake ${OE_QBS_QT_VERSION}
${OE_QBS_CONFIG} --settings-dir ${OE_QBS_SETTINGS_DIR} profiles.${OE_QBS_QT_VERSION}.baseProfile ${TARGET_ARCH}-gcc

}

addtask generate_profile after do_patch before do_configure
