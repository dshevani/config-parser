package com.twitter.project.config.parser;

import java.io.Reader;

import com.twitter.project.config.Config;

public interface ConfigParser {

  // Parse the configuration in given source and return Config object
  Config parse(Reader source);
  
}
