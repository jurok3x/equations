package com.yukotsiuba.equation.repository.impl;

import com.yukotsiuba.equation.dao.IEquationDao;
import com.yukotsiuba.equation.dao.IEquationRootDao;
import com.yukotsiuba.equation.dao.IRootDao;
import com.yukotsiuba.equation.entity.Equation;
import com.yukotsiuba.equation.entity.Root;
import com.yukotsiuba.equation.exception.BadParameterException;
import com.yukotsiuba.equation.repository.IEquationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class EquationRepositoryImpl implements IEquationRepository {

    private IEquationDao equationDao;
    private IRootDao rootDao;
    private IEquationRootDao equationRootDao;
    
    @Override
    public Equation save(Equation equation) {
        if(equationExists(equation)){//add roots if equation exists
            equation.getRoots().forEach(root -> addRoot(equation, root));
            return equation;
        }
        Equation newEquation = equationDao.save(equation);//save new equation
        return newEquation;
    }
    
    private void addRoot(Equation equation, Root root) {
        if(equationExists(equation)){
            if(!rootExists(root)) {
                rootDao.save(root); 
            }
            equationRootDao.save(equation, root);
        }
    }
    
    private boolean rootExists(Root root) {
        return rootDao.findByValue(root.getValue()).isPresent();
    }

    @Override
    public List<Equation> findByRootValues(List<String> values) {
        List<Equation> equations = equationDao.findByRootValues(values);
        for(Equation equation:equations) {
            equation.setRoots(rootDao.findByEquation(equation));
        }
        return equations;
    }

    @Override
    public List<Equation> findByRootsCount(Integer count) {
        if(!validateCount(count)){
            throw new BadParameterException("Wrong count quantity.");
        }
        List<Equation> equations = equationDao.findByRootCount(count);
        for(Equation equation:equations) {
            equation.setRoots(rootDao.findByEquation(equation));
        }
        return equations;
    }

    private boolean equationExists(Equation equation) {
        return equationDao.findByEquationString(equation.getEqString()).isPresent();
    }

    private boolean validateCount(Integer count) {
        if(count == null){
            return false;
        }
        return count >= 0;
    }

    @Override
    public Optional<Equation> findById(Integer id) {
        Optional<Equation> optionalEq = equationDao.findById(id);
        Equation equation = null;
        if(optionalEq.isPresent()) {
            equation = optionalEq.get();
            List<Root> roots = rootDao.findByEquation(equation);
            equation.setRoots(roots);
        }
        return Optional.ofNullable(equation);
    }

}
