package EverGrowth.com.EverGrowthserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EverGrowth.com.EverGrowthserver.bean.UserBean;
import EverGrowth.com.EverGrowthserver.entity.UsuarioEntity;
import EverGrowth.com.EverGrowthserver.exception.ResourceNotFoundException;
import EverGrowth.com.EverGrowthserver.exception.UnauthorizedException;
import EverGrowth.com.EverGrowthserver.helper.JWTHelper;
import EverGrowth.com.EverGrowthserver.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class SesionService {

    @Autowired
    UsuarioRepository  usuarioRepository;

    @Autowired
    HttpServletRequest oHttpServletRequest;

    public String login(UserBean oUserBean) {
        usuarioRepository.findByUsernameAndPassword(oUserBean.getUsername(), oUserBean.getPassword())
                .orElseThrow(() -> new ResourceNotFoundException("Wrong User or password"));
        return JWTHelper.generateJWT(oUserBean.getUsername());
    }

    public String getSessionUsername() {
        if (oHttpServletRequest.getAttribute("username") instanceof String) {
            return oHttpServletRequest.getAttribute("username").toString();
        } else {
            return null;
        }
    }

    public UsuarioEntity getSessionUser() {
        if (this.getSessionUsername() != null) {
            return usuarioRepository.findByUsername(this.getSessionUsername()).orElse(null);
        } else {
            return null;
        }
    }

    public Boolean isSessionActive() {
        if (this.getSessionUsername() != null) {
            return usuarioRepository.findByUsername(this.getSessionUsername()).isPresent();
        } else {
            return false;
        }
    }

    public Boolean isAdmin() {
        if (this.getSessionUsername() != null) {
            UsuarioEntity oUserEntityInSession = usuarioRepository.findByUsername(this.getSessionUsername())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            return Boolean.FALSE.equals(oUserEntityInSession.getrol());
        } else {
            return false;
        }
    }

    public Boolean isUser() {
        if (this.getSessionUsername() != null) {
            UsuarioEntity oUserEntityInSession = usuarioRepository.findByUsername(this.getSessionUsername())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            return Boolean.TRUE.equals(oUserEntityInSession.getrol());
        } else {
            return false;
        }
    }

    public void onlyAdmins() {
        if (!this.isAdmin()) {
            throw new UnauthorizedException("Solo los administradores pueden hacer esto");
        }
    }

    public void onlyUsers() {
        if (!this.isUser()) {
            throw new UnauthorizedException("Solo los usuarios pueden hacer esto");
        }
    }

    public void onlyAdminsOrUsers() {
        if (!this.isSessionActive()) {
            throw new UnauthorizedException("Solo los administradores o usuarios pueden hacer esto");
        }
    }

    public void onlyUsersWithIisOwnData(Long id_user) {
        if (!this.isUser()) {
            throw new UnauthorizedException("Solo los usuarios pueden hacer esto");
        }
        if (!this.getSessionUser().getId().equals(id_user)) {
            throw new UnauthorizedException("Solo los usuarios pueden hacer esto");
        }
    }

    public void onlyAdminsOrUsersWithIisOwnData(Long id_user) {
        if (this.isSessionActive()) {
            if (!this.isAdmin()) {
                if (!this.isUser()) {
                    throw new UnauthorizedException("Solo los administradores o usuarios pueden hacer esto");
                } else {
                    if (!this.getSessionUser().getId().equals(id_user)) {
                        throw new UnauthorizedException("Solo los administradores o usuarios propietarios pueden hacer esto");
                    }
                }
            }
        } else {
            throw new UnauthorizedException("Solo los administradores o usuarios pueden hacer esto");
        }
    }

    
}
