package com.epam.shchuryk.infohandling.parser;

import com.epam.shchuryk.infohandling.entity.Component;

/**
 * Created by msi on 08.02.2018.
 */
public interface ComponentParser {
     Component parse(String data);
     Component chain(String data);

}
