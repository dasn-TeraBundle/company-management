import {Company} from "./company";
import {Department} from "./department";
import {User} from "./user";

export interface Document {
  id: string;
  filename: string;
  mimeType: string;
  company: Company;
  department: Department;
  employee: User;
  uploaded_date: string;
  modified_date: string;
}
