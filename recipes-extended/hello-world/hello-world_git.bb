DESCRIPTION = "Hello World with Qbs"
MAINTAINER = "Raphael Cotty <raphael.cotty@gmail.com>"
SECTION = "x11/apps"
LICENSE = "CLOSED"

inherit qbs

SRC_URI[sha256sum] = "3f0fd831016ddc8c69e7b43f11ef16b0ae439b03"

SRCREV = "8eacf8160f5ce47b431568f2325a7276a67bb8ea"
PV = "0.1+git${SRCPV}"

SRC_URI = "git://github.com/raphaelcotty/hello-world.git;branch=main;protocol=https"

S = "${WORKDIR}/git"

PV = "git${SRCPV}"
