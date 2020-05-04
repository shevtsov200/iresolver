package com.koval.resolver.processor.documentation.core;

import com.koval.resolver.processor.documentation.bean.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class DocTypeDetectorTest {

  private static final String FILE_NAME = "filename";
  private static final String EXTENSION_DELIMITER = ".";

  private static final String PDF_EXTENSION = "pdf";
  private static final String DOC_EXTENSION = "doc";
  private static final String DOCX_EXTENSION = "docx";
  private static final String UNKNOWN_EXTENSION = "zzzz";

  private DocTypeDetector mDocTypeDetector;

  @BeforeEach
  void onSetup() {
    mDocTypeDetector = new DocTypeDetector();
  }

  @ParameterizedTest
  @MethodSource("createFileExtensionsAndExpectedMediaTypes")
  void testDetectingFileExtension(String fileExtension, MediaType expectedMediaType) {
    MediaType detectedType = detectType(fileExtension);

    assertEquals(expectedMediaType, detectedType);
  }

  private MediaType detectType(String fileExtension) {
    String fileName = FILE_NAME + EXTENSION_DELIMITER + fileExtension;

    return mDocTypeDetector.detectType(fileName);
  }

  static Stream<Arguments> createFileExtensionsAndExpectedMediaTypes() {
    return Stream.of(
            arguments(PDF_EXTENSION, MediaType.PDF),
            arguments(DOC_EXTENSION, MediaType.WORD),
            arguments(DOCX_EXTENSION, MediaType.WORD),
            arguments(UNKNOWN_EXTENSION, MediaType.UNKNOWN)
    );
  }
}