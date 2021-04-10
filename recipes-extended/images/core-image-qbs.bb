SUMMARY = "A small image just capable to run a hello-world built with Qbs"

IMAGE_INSTALL = "packagegroup-core-boot \
		hello-world \
		"

TOOLCHAIN_HOST_TASK = "nativesdk-packagegroup-qt5-toolchain-host nativesdk-qbs"
TOOLCHAIN_TARGET_TASK = "packagegroup-qt5-toolchain-target"

inherit core-image
