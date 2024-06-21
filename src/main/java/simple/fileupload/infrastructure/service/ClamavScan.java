package simple.fileupload.infrastructure.service;

import simple.fileupload.exception.VirusFoundException;

import java.io.InputStream;

public interface ClamavScan {
    void scan(InputStream inputStream) throws VirusFoundException;
}
