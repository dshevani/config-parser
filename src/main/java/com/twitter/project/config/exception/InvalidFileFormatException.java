package com.twitter.project.config.exception;

import java.io.IOException;

/**
 * @author deepak.shevani on Jul 11, 2015
 *
 */

public class InvalidFileFormatException extends IOException {
  
    private static final long serialVersionUID = -1L;

    public InvalidFileFormatException(String message) {
        super(message);
    }
    
}