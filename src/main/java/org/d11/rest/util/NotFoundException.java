package org.d11.rest.util;

import org.d11.rest.D11RestVersion;

public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = D11RestVersion.SERIAL_VERSION_UID;

    public NotFoundException() {
        super("Not Found");
    }

}
