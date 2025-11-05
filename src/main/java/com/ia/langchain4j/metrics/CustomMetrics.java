package com.ia.langchain4j.metrics;

import java.util.List;
import java.util.Map;

public class CustomMetrics {

    public static final String BASE_URL_ACTUATOR = "http://localhost:8080/actuator/metrics/";
    public static final String BASE_URL_PROMETHEUS = "http://localhost:9090/api/v1/query";

    public static final Map<String, List<String>> METRIC_CATEGORIES_PROMETHEUS = Map.of(
            "JVM", List.of(
                    "jvm_memory_used_bytes",
                    "jvm_memory_max_bytes",
                    "jvm_memory_committed_bytes",
                    "jvm_gc_pause_seconds_sum",
                    "jvm_gc_pause_seconds_count",
                    "jvm_threads_current",
                    "jvm_threads_daemon",
                    "jvm_threads_peak",
                    "jvm_classes_loaded",
                    "jvm_classes_unloaded",
                    "jvm_buffer_pool_used_bytes",
                    "jvm_memory_pool_bytes_used"
            ),
            "CPU", List.of(
                    "process_cpu_usage",
                    "process_cpu_seconds_total",
                    "system_cpu_usage",
                    "node_cpu_seconds_total",
                    "node_load1",
                    "node_load5",
                    "node_load15",
                    "process_resident_memory_bytes",
                    "process_virtual_memory_bytes",
                    "process_open_fds",
                    "process_max_fds"
            ),
            "Disco", List.of(
                    "node_filesystem_avail_bytes",
                    "node_filesystem_size_bytes",
                    "node_filesystem_used_bytes",
                    "node_disk_io_time_seconds_total",
                    "node_disk_read_bytes_total",
                    "node_disk_written_bytes_total",
                    "node_disk_reads_completed_total",
                    "node_disk_writes_completed_total"
            ),
            "Rede", List.of(
                    "node_network_receive_bytes_total",
                    "node_network_transmit_bytes_total",
                    "node_network_receive_errs_total",
                    "node_network_transmit_errs_total"
            ),
            "HTTP", List.of(
                    "http_server_requests_seconds_count",
                    "http_server_requests_seconds_sum",
                    "http_server_requests_seconds_bucket",
                    "http_server_requests_total",
                    "http_server_errors_total",
                    "http_server_active_requests"
            ),
            "Tomcat", List.of(
                    "tomcat_sessions_active_current",
                    "tomcat_sessions_created_total",
                    "tomcat_sessions_expired_total",
                    "tomcat_threads_busy",
                    "tomcat_threads_current",
                    "tomcat_global_request_max",
                    "tomcat_global_request_count"
            ),
            "Inicialização", List.of(
                    "process_start_time_seconds",
                    "jvm_uptime_seconds"
            ),
            "Processo", List.of(
                    "process_start_time_seconds",
                    "process_cpu_seconds_total"
            )
    );

    public static final Map<String, List<String>> METRIC_CATEGORIES_ACTUATOR = Map.of(
            "Inicialização", List.of(
                    "application.started.time",
                    "application.ready.time"
            ),
            "JVM", List.of(
                    "jvm.memory.used",
                    "jvm.memory.max",
                    "jvm.memory.committed",
                    "jvm.gc.memory.allocated",
                    "jvm.gc.memory.promoted",
                    "jvm.gc.overhead",
                    "jvm.threads.live",
                    "jvm.threads.daemon",
                    "jvm.threads.peak",
                    "jvm.classes.loaded",
                    "jvm.classes.unloaded",
                    "jvm.info"
            ),
            "Executor", List.of(
                    "executor.active",
                    "executor.completed",
                    "executor.pool.size",
                    "executor.pool.max",
                    "executor.pool.core",
                    "executor.queue.remaining",
                    "executor.queued"
            ),
            "HTTP e Tomcat", List.of(
                    "http.server.requests",
                    "http.server.requests.active",
                    "tomcat.sessions.active.current",
                    "tomcat.sessions.created",
                    "tomcat.sessions.expired",
                    "tomcat.sessions.rejected"
            ),
            "Disco e Sistema", List.of(
                    "disk.free",
                    "disk.total",
                    "system.cpu.usage",
                    "system.cpu.count"
            ),
            "Processo e CPU", List.of(
                    "process.cpu.usage",
                    "process.cpu.time",
                    "process.uptime",
                    "process.start.time"
            ),
            "Outros", List.of(
                    "logback.events"
            )
    );
}
