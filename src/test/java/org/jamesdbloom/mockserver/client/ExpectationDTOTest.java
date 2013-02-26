package org.jamesdbloom.mockserver.client;

import org.jamesdbloom.mockserver.matchers.HttpRequestMatcher;
import org.jamesdbloom.mockserver.matchers.Times;
import org.jamesdbloom.mockserver.model.HttpResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.VerboseMockitoJUnitRunner;

import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.verify;

/**
 * @author jamesdbloom
 */
@RunWith(VerboseMockitoJUnitRunner.class)
public class ExpectationDTOTest {

    @Mock
    private MockServerClient mockServerClient;

    @Test
    public void createRequestAndResponseExpectation() {
        // given
        HttpRequestMatcher httpRequestMatcher = new HttpRequestMatcher();
        Times times = Times.unlimited();
        HttpResponse httpResponse = new HttpResponse();

        // when
        ExpectationDTO expectationDTO = new ExpectationDTO(mockServerClient, httpRequestMatcher, times);
        expectationDTO.respond(httpResponse);

        // then
        verify(mockServerClient).sendExpectation(same(expectationDTO));
        assertSame(httpRequestMatcher, expectationDTO.getHttpRequestMatcher());
        assertSame(times, expectationDTO.getTimes());
        assertSame(httpResponse, expectationDTO.getHttpResponse());
    }

}