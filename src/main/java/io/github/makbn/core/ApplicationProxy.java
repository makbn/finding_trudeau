package io.github.makbn.core;

import io.github.makbn.FindingTrudeauConfiguration;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;
import java.util.List;

/**
 * proxy requests if news providers are banned by internet provider
 * by Mehdi Akbarian Rastaghi , 20/4/11
 **/

@Slf4j
public class ApplicationProxy {

    public ApplicationProxy(FindingTrudeauConfiguration configuration) {
        init(configuration);
    }

    public void init(FindingTrudeauConfiguration configuration) {
        if (configuration.getProxy().endsWith("NO_PROXY"))
            return;

        log.warn("proxy loaded!");
        ProxySelector.setDefault(new ProxySelector() {
            final ProxySelector delegate = ProxySelector.getDefault();

            @Override
            public List<Proxy> select(URI uri) {
                try {
                    String[] proxy = configuration.getProxy().split(":");
                    return Arrays.asList(new Proxy(Proxy.Type.HTTP, InetSocketAddress
                            .createUnresolved(proxy[0], Integer.parseInt(proxy[1]))));
                } catch (Exception e) {
                    log.error(getClass().getSimpleName(), e);
                }

                // revert to the default behaviour
                return delegate == null ? Arrays.asList(Proxy.NO_PROXY)
                        : delegate.select(uri);
            }

            @Override
            public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
                log.error(getClass().getSimpleName(), ioe);
            }
        });
    }


}
