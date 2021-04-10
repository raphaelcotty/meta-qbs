DESCRIPTION = "Hello World with Qbs"
MAINTAINER = "Raphael Cotty <raphael.cotty@gmail.com>"
SECTION = "x11/apps"
LICENSE = "CLOSED"

inherit qbs

SRC_URI[sha256sum] = "3f0fd831016ddc8c69e7b43f11ef16b0ae439b03"

SRCREV = "dc1d19c2cce1cab826e51247f2eaf9f7190f844a"
PV = "0.1+git${SRCPV}"

SRC_URI = "gitsm:///home/raph/src/hello-world/;branch=main;protocol=file"

S = "${WORKDIR}/git"

PV = "git${SRCPV}"
