package com.curso.services;

import com.curso.domains.Pessoa;
import com.curso.repositories.PessoaRepository;
import com.curso.security.UserSS;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PessoaRepository pessoaRepo;

    public UserDetailsServiceImpl(PessoaRepository pessoaRepo) {
        this.pessoaRepo = pessoaRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Optional<Pessoa> user = pessoaRepo.findByEmail(username);
        if (user.isEmpty()){
            throw new UsernameNotFoundException("User not fond: "+ username);
        }
        return new UserSS(user.orElse(null));
    }
}
