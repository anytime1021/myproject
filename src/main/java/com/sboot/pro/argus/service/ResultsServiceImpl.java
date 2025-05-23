package com.sboot.pro.argus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("resultsService")
@Transactional(propagation = Propagation.REQUIRED)
public class ResultsServiceImpl implements ResultsService {

}
