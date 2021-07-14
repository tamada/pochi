package jp.cafebabe.pochi;

import java.util.HashMap;
import java.util.Map;

class Environment {
    private Map<String, String> envs;

    public Environment() {
        this(new HashMap<>());
    }

    public Environment(Map<String, String> envs) {
        this.envs = envs;
    }

    public String getenv(String key) {
        return envs.getOrDefault(key, System.getenv(key));
    }

    public void putenv(String key, String value) {
        this.envs.put(key, value);
    }

    public void putAll(Map<String, String> giveEnvs) {
        giveEnvs
                .forEach((key, value) -> envs.put(key, value));
    }
}
