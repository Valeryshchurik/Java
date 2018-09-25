package com.shchuryk.command;
public enum CommandEnum {
        LOGIN {
            {
                this.command = new LoginCommand();
            }
        },
        LOGOUT {
            {
                this.command = new LogoutCommand();
            }
        },
        REGISTER {
            {
                this.command = new RegisterCommand();
            }
        },
        ALL_TOURS{
            {
                this.command = new AllToursCommand();
            }
        },
        BURNING_TOURS{
            {
                this.command = new BurningToursCommand();
            }
        },
        USERS{
            {
                this.command = new UsersCommand();
            }
        },
        ORDERS{
            {
                this.command = new OrderCommand();
            }
        },
        EDITTOUR {
            {
                this.command = new EditTourCommand();
            }
        };
        ActionCommand command;
        public ActionCommand getCurrentCommand() {
            return command;
        }
}
