package com.ia.langchain4j.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

@RestController
@RequestMapping("/simulate")
public class SimulationController {

    // Lista para reter referências e simular consumo de memória
    private static final List<byte[]> allocations = Collections.synchronizedList(new ArrayList<>());

    /**
     * Simula consumo de memória alocando 'sizeMb' megabytes.
     * Parâmetros:
     * - sizeMb (int, opcional, default 200)
     * - retain (boolean, opcional, default true) : se true, mantém referência para evitar GC
     */
    @PostMapping("/memory")
    public ResponseEntity<String> consumeMemory(
            @RequestParam(name = "sizeMb", defaultValue = "200") int sizeMb,
            @RequestParam(name = "retain", defaultValue = "true") boolean retain) {

        if (sizeMb <= 0) {
            return ResponseEntity.badRequest().body("sizeMb deve ser maior que 0");
        }

        try {
            byte[] chunk = new byte[sizeMb * 1024 * 1024];
            if (retain) {
                allocations.add(chunk);
            }
            return ResponseEntity.ok("Alocado " + sizeMb + " MB. Retain=" + retain + ". Total allocations: " + allocations.size());
        } catch (OutOfMemoryError oom) {
            return ResponseEntity.status(500).body("Falha ao alocar memória: OutOfMemoryError");
        } catch (Throwable t) {
            return ResponseEntity.status(500).body("Erro ao alocar memória: " + t.getMessage());
        }
    }

    /**
     * Simula alto tempo de resposta com Thread.sleep.
     * Parâmetros:
     * - ms (long, opcional, default 5000) : milissegundos de espera
     */
    @GetMapping("/latency")
    public ResponseEntity<String> highLatency(@RequestParam(name = "ms", defaultValue = "5000") long ms) {
        if (ms < 0) {
            return ResponseEntity.badRequest().body("ms deve ser >= 0");
        }
        try {
            Thread.sleep(ms);
            return ResponseEntity.ok("Delay concluído: " + ms + " ms");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return ResponseEntity.status(500).body("Interrompido durante o sleep");
        }
    }

    /**
     * Simula consumo alto de CPU.
     * Parâmetros:
     * - durationSeconds (int, opcional, default 30) : duração da carga em segundos
     * - threads (int, opcional, default = número de processors) : quantidade de threads de carga
     * - async (boolean, opcional, default false) : se true, lança a carga em background e retorna imediatamente
     */
    @PostMapping("/cpu")
    public ResponseEntity<String> consumeCpu(
            @RequestParam(name = "durationSeconds", defaultValue = "30") int durationSeconds,
            @RequestParam(name = "threads", required = false) Integer threads,
            @RequestParam(name = "async", defaultValue = "false") boolean async) {

        if (durationSeconds <= 0) {
            return ResponseEntity.badRequest().body("durationSeconds deve ser > 0");
        }

        int cpuThreads = (threads == null || threads <= 0) ? Runtime.getRuntime().availableProcessors() : threads;
        final long endTime = System.nanoTime() + TimeUnit.SECONDS.toNanos(durationSeconds);

        Runnable task = () -> {
            while (System.nanoTime() < endTime) {
                // trabalho leve para consumir CPU
                double x = Math.random();
                double y = Math.random();
                double z = Math.pow(x, y);
                // evita otimização completa
                if (z == -1.0) {
                    System.out.println(z);
                }
            }
        };

        ExecutorService executor = Executors.newFixedThreadPool(cpuThreads);

        if (async) {
            for (int i = 0; i < cpuThreads; i++) {
                executor.submit(task);
            }
            // Não aguarda término: encerra executor em background quando a JVM for encerrada
            executor.shutdown();
            return ResponseEntity.ok("Carga de CPU iniciada em background por " + durationSeconds + " segundos em " + cpuThreads + " threads");
        } else {
            List<Callable<Void>> callables = new ArrayList<>();
            for (int i = 0; i < cpuThreads; i++) {
                callables.add(() -> {
                    task.run();
                    return null;
                });
            }
            try {
                executor.invokeAll(callables);
                executor.shutdown();
                return ResponseEntity.ok("Carga de CPU concluída por " + durationSeconds + " segundos em " + cpuThreads + " threads");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                executor.shutdownNow();
                return ResponseEntity.status(500).body("Interrompido durante a carga de CPU");
            }
        }
    }
}
