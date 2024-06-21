package simple.fileupload.infrastructure.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import simple.fileupload.exception.VirusFoundException;
import simple.fileupload.infrastructure.service.ClamavScan;
import xyz.capybara.clamav.ClamavClient;
import xyz.capybara.clamav.commands.scan.result.ScanResult;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class ClamavScanImpl implements ClamavScan {

    private final ClamavClient clamavClient;
    public void scan(InputStream inputStream) throws VirusFoundException {
        ScanResult result = clamavClient.scan(inputStream);
        if (result instanceof ScanResult.VirusFound) {
            throw new VirusFoundException();
        }
    }

}
