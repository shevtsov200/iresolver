package com.koval.resolver.processor.documentation.core;

import com.koval.resolver.processor.documentation.bean.DocFile;

public class DocDataLineParser implements LineParser<DocFile> {

  private final String delimiter;

  public DocDataLineParser(final String delimiter) {
    this.delimiter = delimiter;
  }

  @Override
  public DocFile parseLine(final String line) {
    String[] split = line.split(delimiter);

    int fileIndex = Integer.parseInt(split[0]);
    String fileName = split[1];

    return new DocFile(fileIndex, fileName);
  }
}

