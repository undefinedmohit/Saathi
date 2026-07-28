package com.saathi;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.*;

@Service
public class FileWatcherService {

    @Value("${saathi.watch.path}")
    private String watchPath;

    private final OllamaService ollamaService;

    public FileWatcherService(OllamaService ollamaService) {
        this.ollamaService = ollamaService;
    }

    @PostConstruct
    public void startWatching() {
        Thread watcherThread = new Thread(this::watch);
        watcherThread.setDaemon(true);
        watcherThread.start();
        System.out.println("Saathi is watching: " + watchPath);
    }

    private void watch() {
    try {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path path = Paths.get(watchPath);
        path.register(watchService,
                StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_CREATE);

        while (true) {
            WatchKey key = watchService.take();
            for (WatchEvent<?> event : key.pollEvents()) {
                System.out.println("🔍 Event detected: " + event.kind() + " -> " + event.context());

                Path changedFile = (Path) event.context();
                String fileName = changedFile.toString();

                if (fileName.endsWith(".java")) {
                    Path fullPath = path.resolve(changedFile);
                    if (Files.exists(fullPath)) {
                        System.out.println("\n📝 File changed: " + fileName);
                        String content = Files.readString(fullPath);
                        ollamaService.getSuggestion(content);
                    }
                }
            }
            key.reset();
        }
    } catch (IOException | InterruptedException e) {
        e.printStackTrace();
    }
}
}