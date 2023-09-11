package edu.milenaalcantara.controller;

import edu.milenaalcantara.model.ProfessorModel;
import edu.milenaalcantara.repository.CursoRepository;

public class CursoController {
    CursoRepository cursoRepo = new CursoRepository();
    ProfessorModel professorModel = new ProfessorModel();

   public Boolean consultarCursosPorProfessor(String nomeProfessor){
       professorModel.setNomeProfessor(nomeProfessor);
       return cursoRepo.consultarCursosPorProfessor(professorModel.getNomeProfessor());
   }

   public Boolean  consultarCursosSemAlunosMatriculados(){
       return cursoRepo.consultarCursosSemAlunosMatriculados();
   }
}
