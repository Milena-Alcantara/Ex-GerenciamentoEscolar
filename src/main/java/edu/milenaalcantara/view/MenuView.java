package edu.milenaalcantara.view;
import edu.milenaalcantara.controller.AlunoController;
import edu.milenaalcantara.controller.CursoController;
import java.util.Scanner;
public class MenuView {
    AlunoController alunoController;
    CursoController cursoController;
    Scanner scan;

    public MenuView() {
        alunoController = new AlunoController();
        cursoController = new CursoController();
        scan = new Scanner(System.in);
    }
    public void MenuPrincipal() {
        int resp;
        do {
            System.out.println("\t\t\t\tVocê deseja: \n------------------------------------------------------");
            System.out.println("\t[1] Consultar alunos matriculados em um curso específico \n\t[2] Consultar cursos ministrados por um professor " + "\n\t[3] Consultar alunos que não estão matriculados em nenhum curso  \n\t[4] Consultar cursos sem alunos matriculados " + "\n\t[5] Consultar alunos matriculados em mais de um curso \n\t[0] SAIR");
            resp = scan.nextInt();
            switch (resp) {
                case 1:
                    System.out.print("Informe o nome do curso: ");
                    scan.nextLine();
                    String nomeCurso = scan.nextLine();
                    alunoController.consultarAlunosPorCurso(nomeCurso);
                    break;
                case 2:
                    System.out.print("Informe o nome do professor: ");
                    scan.nextLine();
                    String nomeProfessor = scan.nextLine();
                    cursoController.consultarCursosPorProfessor(nomeProfessor);
                    break;
                case 3:
                    alunoController.consultarAlunosNaoMatriculados();
                    break;
                case 4:
                    cursoController.consultarCursosSemAlunosMatriculados();
                    break;
                case 5:
                    alunoController.consultarAlunosMatriculadosEmMaisDeUmCurso();
                    break;
                case 0:
                    System.out.println("Encerrando Sistema...");
                    System.exit(0);
                default:
                    System.err.println("Opção Inválida! Tente novamente... \n");
            }
        } while (resp != 0);
    }
}
