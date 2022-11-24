package com.example.block11uploaddownloadfiles;

import com.example.block11uploaddownloadfiles.file.service.FileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// Class that check if a parameter was set in execution
@Component
public class ExecutionParameter implements CommandLineRunner {
    @Autowired
    FileServiceImpl fileServiceImpl;

    // If there is a path parameter, set that path with setDownloadPath() method
    @Override
    public void run(String... args) throws Exception {
        if(args.length!=0){
            fileServiceImpl.setDownloadPath(args[0]);
        }
    }
}
