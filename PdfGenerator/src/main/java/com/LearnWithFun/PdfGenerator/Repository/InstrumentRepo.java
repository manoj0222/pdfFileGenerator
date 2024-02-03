/**
 * 
 */
package com.LearnWithFun.PdfGenerator.Repository;

import org.springframework.data.repository.CrudRepository;

import com.LearnWithFun.PdfGenerator.entities.Instrument;

/**
 * @author Manoj Kumar.B
 *
 */
public interface InstrumentRepo extends CrudRepository<Instrument, Integer> {

}
