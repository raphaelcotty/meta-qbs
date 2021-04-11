DESCRIPTION = "Hello World with Qbs"
MAINTAINER = "Raphael Cotty <raphael.cotty@gmail.com>"
SECTION = "x11/apps"
LICENSE = "CLOSED"

inherit qbs

SRC_URI[sha256sum] = "3f0fd831016ddc8c69e7b43f11ef16b0ae439b03"

SRCREV = "efb81c6424120181a744fa65c0101bedbdb1c8c8"
PV = "0.1+git${SRCPV}"

SRC_URI = "git://github.com/raphaelcotty/hello-world.git;branch=main;protocol=https"

S = "${WORKDIR}/git"

PV = "git${SRCPV}"
