import {User} from "./user";
import {Company} from "./company";

export class Department {

  id: string;;
  name: string;
  admin: User;
  company: Company;
}
