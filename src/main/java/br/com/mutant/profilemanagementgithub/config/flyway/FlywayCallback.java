package br.com.mutant.profilemanagementgithub.config.flyway;

import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.api.MigrationInfo;
import org.flywaydb.core.api.callback.Callback;
import org.flywaydb.core.api.callback.Context;
import org.flywaydb.core.api.callback.Event;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FlywayCallback implements Callback {

    @Override
    public boolean supports(Event event, Context context) {
        return event == Event.BEFORE_MIGRATE ||
                event == Event.AFTER_MIGRATE ||
                event == Event.AFTER_MIGRATE_ERROR;
    }

    @Override
    public boolean canHandleInTransaction(Event event, Context context) {
        return true;
    }

    @Override
    public String getCallbackName() {
        return "FlywayCustomLogger";
    }

    @Override
    public void handle(Event event, Context context) {
        switch (event) {
            case BEFORE_MIGRATE -> logMigrationStart(context);
            case AFTER_MIGRATE -> logSuccessMigration(context);
            case AFTER_MIGRATE_ERROR -> logMigrationError(context);
        }
    }

    private void logMigrationStart(Context context) {
        log.info("Starting database migrations...");
        log.debug("Migration target: {}", context.getConfiguration().getTarget());
    }

    private void logSuccessMigration(Context context) {
        MigrationInfo info = context.getMigrationInfo();
        if (info != null) {
            log.info("Migration completed successfully!");
            logMigrateVersion(info);
        }
    }

    private void logMigrationError(Context context) {
        MigrationInfo info = context.getMigrationInfo();
        if (info != null) {
            log.error("Migration failed!");
            log.error("Error in migration: {} - {}",
                    info.getVersion(),
                    info.getDescription());
            logMigrateVersion(info);
        } else {
            log.error("Error during migration - migration information not available");
        }
    }

    private void logMigrateVersion(MigrationInfo info) {
        log.debug("Version: {} | Description: {} | Checksum: {}",
                info.getVersion(),
                info.getDescription(),
                info.getChecksum());
    }
}