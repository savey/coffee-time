package com.java.coffeetime.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Savey
 * @date 2023/4/12/16:05
 */
public class BeautifulCountryTarget implements Fight {


    private static final Logger LOGGER = LoggerFactory.getLogger(BeautifulCountryTarget.class);


    /**
     * 射击
     */
    @Override
    public void shot() {
        
        LOGGER.debug("M4 shot ---> big goose!");
        
    }

    /**
     * 炸弹
     */
    @Override
    public void bomb() {
        
        LOGGER.debug("HIMARS fire ---> big goose!");
    }
}
