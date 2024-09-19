package eprocument.common.role;

import ifmis.framework.common.trace.AbstractRequestEvent;

public class AbstractRoleEvent extends AbstractRequestEvent {

        private final RoleDto dto;

        protected AbstractRoleEvent(final Object source, final RoleDto dto) {
            super(source);
            this.dto = dto;
        }

        public RoleDto getDto() {
            return dto;
        }
}
