package edu.milenaalcantara.controller;

import edu.milenaalcantara.model.CursoModel;
import edu.milenaalcantara.repository.AlunoRepository;

public class AlunoController {
    AlunoRepository alunoRepo = new AlunoRepository();
    CursoModel cursoModel = new CursoModel();

    public Boolean consultarAlunosPorCurso(String nomeCurso){
        cursoModel.setNomeCurso(nomeCurso);
        return alunoRepo.consultarAlunosPorCurso(cursoModel.getNomeCurso());
    }

    public Boolean consultarAlunosNaoMatriculados(){
        return alunoRepo.consultarAlunosNaoMatriculados();
    }

    public Boolean consultarAlunosMatriculadosEmMaisDeUmCurso(){
        return alunoRepo.consultarAlunosMatriculadosEmMaisDeUmCurso();
    }
}
