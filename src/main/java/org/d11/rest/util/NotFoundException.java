package org.d11.rest.util;

import org.d11.rest.api.D11RestApiVersion;

public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = D11RestApiVersion.SERIAL_VERSION_UID;

    public NotFoundException() {
        super("Not Found");
    }

}
