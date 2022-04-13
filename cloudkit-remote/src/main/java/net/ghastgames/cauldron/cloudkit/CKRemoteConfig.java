package net.ghastgames.cauldron.cloudkit;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CKRemoteConfig {
    final String redisHost;
    final String redisPort;
    final String redisPassword;
}
