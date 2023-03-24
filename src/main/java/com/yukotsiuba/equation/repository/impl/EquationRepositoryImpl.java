package com.yukotsiuba.equation.repository.impl;

import com.yukotsiuba.equation.dao.IEquationDao;
import com.yukotsiuba.equation.entity.Equation;
import com.yukotsiuba.equation.entity.Root;
import com.yukotsiuba.equation.exception.BadParameterException;
import com.yukotsiuba.equation.exception.EquationAlreadyExistsException;
import com.yukotsiuba.equation.repository.IEquationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class EquationRepositoryImpl implements IEquationRepository {

    private IEquationDao equationDao;
    @Override
    public Equation save(Equation equation) {
        if(!equationExists(equation)){
            throw new EquationAlreadyExistsException(String.format("Equation %s already exists.", equation.getEqString()));
        }
        Equation neqEquation = equationDao.save(equation);
        return neqEquation;
    }

    @Override
    public List<Equation> findByRoots(List<Root> roots) {
        return equationDao.findByRoots(roots);
    }

    @Override
    public List<Equation> findByRootsCount(Integer count) {
        if(!validateCount(count)){
            throw new BadParameterException("Wrong count quantity.");
        }
        return equationDao.findByRootCount();
    }

    private boolean equationExists(Equation equation) {
        return equationDao.findById(equation.getId()).isPresent();
    }

    private boolean validateCount(Integer count) {
        if(count == null){
            return false;
        }
        return count >= 0;
    }
}
