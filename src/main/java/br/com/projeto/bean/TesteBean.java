package br.com.projeto.bean;

import br.com.projeto.annotations.Transactional;
import br.com.projeto.persistence.daointerfaces.DAO;
import br.com.projeto.persistence.model.Projeto;
import br.com.projeto.util.FacesUtil;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import static br.com.projeto.util.StringUtil.likeLower;

/**
 * Created by jaels on 20/10/2016.
 */

@Named
@ViewScoped
public class TesteBean implements Serializable {

    @Inject
    private DAO<Projeto> dao;

    private Projeto projeto;
    private List<Projeto> projetos;

    @PostConstruct
    public void init(){
        projetos=dao.getAll();
    }

    @Transactional
    public void inserir(){
        Projeto p = new Projeto();
        p.setNome("Projeto 1");
        dao.save(p);
        p=new Projeto();
        p.setNome("Projeto A");
        dao.save(p);
        FacesUtil.AddSuccessMessege("sucesso");
    }

    public void lista(){
        List<Projeto> projetos = dao.findByHQLQuery("searchProjectByName", Collections.singletonList(likeLower("Projeto 1")), 0 );
        System.out.println(projetos);
    }


    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public List<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(List<Projeto> projetos) {
        this.projetos = projetos;
    }
}
