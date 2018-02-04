package br.biblioteca.livros.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.biblioteca.livros.beans.Emprestimo;
import br.biblioteca.livros.repository.EmprestimoRepository;

@Service
public class EmprestimoServiceImpl implements EmprestimoInterface {
	@Autowired
    private EmprestimoRepository emprestimoRepository;

    @Override
    public List<Emprestimo> findAll() {
        return emprestimoRepository.findAll();
    }

	@Override
	public void save(Emprestimo emprestimo) {
		emprestimoRepository.save(emprestimo);
	}
}
