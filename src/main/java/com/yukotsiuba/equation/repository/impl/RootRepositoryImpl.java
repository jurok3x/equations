package com.yukotsiuba.equation.repository.impl;

import com.yukotsiuba.equation.dao.IEquationDao;
import com.yukotsiuba.equation.dao.IEquationRootDao;
import com.yukotsiuba.equation.dao.IRootDao;
import com.yukotsiuba.equation.entity.Equation;
import com.yukotsiuba.equation.entity.Root;
import com.yukotsiuba.equation.repository.IRootRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class RootRepositoryImpl implements IRootRepository {

    private IEquationDao equationDao;
    private IRootDao rootDao;
    private IEquationRootDao equationRootDao;

    @Override
    public Root add(Equation equation, Root root) {
        if(equationExists(equation) && !rootExists(root)){
            rootDao.save(root);
            equationRootDao.save(equation, root);
        }
        return root;
    }

    @Override
    public List<Root> addAll(Equation equation, List<Root> roots) {
        roots.forEach(root -> add(equation, root));
        return roots;
    }

    private boolean equationExists(Equation equation) {
        return equationDao.findById(equation.getId()).isPresent();
    }

    private boolean rootExists(Root root) {
        return rootDao.findByValue(root.getValue()).isPresent();
    }
}
