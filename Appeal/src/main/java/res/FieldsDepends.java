package res;

import java.util.LinkedHashMap;
import java.util.Map;

import domain.CauseL;
import domain.Rectif1L;
import domain.Rectif2L;
import domain.Rectif3L;
import domain.TypeL;

public class FieldsDepends {
	
	public Map<Integer, TypeL> types = new LinkedHashMap<Integer, TypeL>();
	public Map<Integer, CauseL> causes = new LinkedHashMap<Integer, CauseL>();
	public Map<Integer, Rectif1L> rectifs1 = new LinkedHashMap<Integer, Rectif1L>();
	public Map<Integer, Rectif2L> rectifs2 = new LinkedHashMap<Integer, Rectif2L>();
	public Map<Integer, Rectif3L> rectifs3 = new LinkedHashMap<Integer, Rectif3L>();
	
	public FieldsDepends() {
	
		TypeL type = new TypeL("������", 1);
		type.addCause("3 ����������� �������� ���", 1)
				.addCause("4 ����� �� � ����� ���", 2)
				.addCause("5 ����� �����", 3)
				.addCause("6 ����� ��� ������ ���", 4)
				.addCause("7 ����������� ������ ��", 5)
				.addCause("8 ���������-������������� ��������� ��", 6)
				.addCause("9 �����������-����������� ����������� ��", 7)
				.addCause("10 ����� � ����������� ����������� ����������", 8)
				.addCause("11 ���", 9)
				.addCause("12 ������������� ����������� ��� �������� ����������� ������", 10)
				.addCause("13 ����� � ����������� ������ �� ���������� ���", 11)
				.addCause("14 ������������ ��� ������������ �� ��������", 12)
				.addCause("15 �������� �������� ������� �� ����������� ������ �� ���������� ���", 13)
				.addCause("16 ������������� ��������������� ������������ ������", 14)
				.addCause("17 ������ �������", 15);
		this.types.put(type.getId(), type);
		type = new TypeL("���������", 2);
		type.addCause("3.1 � ��������� ������� ��� ������ ��� ������ � ������ ��������������� ��������� ��������������� �������� �������� ���������� ����������� ������", 16)
				.addCause("3.2 ������ � ������ ���", 17)
				.addCause("3.3 ����������� � ����������� � �������� ��������������� ����", 18)
				.addCause("3.4 ����������� �� ������������� � �������� ��������������� ����", 19)
				.addCause("3.5 � ������ ��������� (��������������) ������ ���", 20)
				.addCause("3.6 ������", 21);
		this.types.put(type.getId(), type);
		type = new TypeL("������������", 3);
		type.addCause("4.1 �� ����������� �������� ���", 22)
				.addCause("4.2 � ������ �� � ����� ���", 23)
				.addCause("4.3 � ������ �����", 24)
				.addCause("4.4 � ������ ��� ������ ���", 25)
				.addCause("4.5 �� ����������� ������ ��", 26)
				.addCause("4.6 � ���������-������������� ��������� ��", 27)
				.addCause("4.7 �� ����� � ����������� ����������� ����������", 28)
				.addCause("4.8 � ���", 29)
				.addCause("4.9 � ������������� ����������� ��� �������� ����������� ������", 30)
				.addCause("4.10 �� ������ � �������� ����������� ������ �� ���������� ���", 31)
				.addCause("4.11 � ��������� ����������� ������ �� ������� ��������� ��� ��� ���������� �����������", 32)
				.addCause("4.12 � �������� �������� ������� �� ����������� ������ �� ���������� ��C", 33)
				.addCause("4.12. 1 � �����, �������� � �������� �������������� ����������� ������ �� ���������� ���", 36)				
				.addCause("4.13 � ������� ����������� �������, ����������� � ��", 34)
				.addCause("4.14 ������", 35);
		this.types.put(type.getId(), type);
		type = new TypeL("�����������", 4);
		this.types.put(type.getId(), type);
		
		CauseL cause = new CauseL("3 ����������� �������� ���", 1);
		cause
				.addRectif1("����� � ������ ������ ���", 1)
				.addRectif1("����������������� ������ ������ ���(����� ������ ��������)", 2)
				.addRectif1("������", 3);
		this.causes.put(cause.getId(), cause);
		cause = new CauseL("4 ����� �� � ����� ���", 2);
		cause
				.addRectif1("4.1 �� ���������� �����������", 5)
				.addRectif1("4.2 ��� ���������� �����������", 4);
		this.causes.put(cause.getId(), cause);
		cause = new CauseL("5 ����� �����", 3);
		cause
				.addRectif1("����� � ������ �����", 6)
				.addRectif1("������ (� ����������)", 7);
		this.causes.put(cause.getId(), cause);
		cause = new CauseL("6 ����� ��� ������ ���", 4);
		cause
				.addRectif1("6.1 �� ����������� ����� ����������", 8)
				.addRectif1("6.2 ��� ����������� ����� ����������", 9)
				.addRectif1("6.3 ��� ����������� �� ���������� ��", 10);
		
		this.causes.put(cause.getId(), cause);
		
		cause = new CauseL("7 ����������� ������ ��", 5);
		cause
				.addRectif1("���������", 11)
				.addRectif1("�����������", 12)
				.addRectif1("������� ���������", 77713);
		
				
		this.causes.put(cause.getId(), cause);
		cause = new CauseL("11 ���", 9);
		cause
				.addRectif1("������-���������", 13)
				.addRectif1("����������", 14)
				.addRectif1("������������-������������", 15)
				.addRectif1("���� ����� ��������� ��������", 16)
				.addRectif1("���������������", 17)
				.addRectif1("���������", 18)
				.addRectif1("�������", 19)
				.addRectif1("����������������", 20)
				.addRectif1("������� ���������", 21)
				.addRectif1("������� �������", 22)
				.addRectif1("������� ����������", 23)
				.addRectif1("������� ������-��������", 24)
				.addRectif1("������� ������", 25)
				.addRectif1("������� ������������", 26)
				.addRectif1("����������", 27)
				.addRectif1("������������", 28)
				.addRectif1("���������", 29)
				.addRectif1("��������", 30)
				.addRectif1("�����������", 31)
				.addRectif1("����������", 32)
				.addRectif1("��������", 33)
				.addRectif1("�������", 34)
				.addRectif1("�����������������", 35)
				.addRectif1("�����������", 36)
				.addRectif1("�������", 37)
				.addRectif1("�����������", 38)
				.addRectif1("����������", 39)
				.addRectif1("��������-���������� ������", 40)
				.addRectif1("����������", 41)
				.addRectif1("��������-�������������", 42)
				.addRectif1("��������", 43)
				.addRectif1("����������", 44)
				.addRectif1("����������� ������", 45)
				.addRectif1("�����������-�������", 46)
				.addRectif1("������", 47)
				.addRectif1("������", 48)
				.addRectif1("��������-������� ������", 49)
				.addRectif1("������������", 50)
				.addRectif1("���� ���", 70)
				.addRectif1("���� �������������� �����������", 71)
				.addRectif1("�����������", 72);
		
		this.causes.put(cause.getId(), cause);
		cause = new CauseL("12 ������������� ����������� ��� �������� ����������� ������", 10);
		cause
				.addRectif1("������������ ����������� ������", 51)
				.addRectif1("�������������������� ����������� ������", 52)
				.addRectif1("�����������-��������������� ����������� ������", 53)
				.addRectif1("������ ����������� ������", 54);
		this.causes.put(cause.getId(), cause);
		cause = new CauseL("13 ����� � ����������� ������ �� ���������� ���", 11);
		cause
				.addRectif1("13.1 �� ���������� �����������", 55)
				.addRectif1("13.2 ��� ���������� �����������", 56);
		this.causes.put(cause.getId(), cause);
		cause = new CauseL("15 �������� �������� ������� �� ����������� ������ �� ���������� ���", 13);
		cause
				.addRectif1("�� ���������� ����������� (15.1)", 58)
				.addRectif1("��� ���������� ����������� (15.2)", 59);
		this.causes.put(cause.getId(), cause);
		cause = new CauseL("17 ������ �������", 15);
		cause
				.addRectif1("� ��� ����� �� ��������, �� ����������� � ����� ���", 60);
		this.causes.put(cause.getId(), cause);
		cause = new CauseL("3.2 ������ � ������ ���", 17);
		cause
				.addRectif1("3.2.1 � ������ ���", 61)
				.addRectif1("3.2.2 � ������ ���", 62);
		this.causes.put(cause.getId(), cause);
		cause = new CauseL("3.5 � ������ ��������� (��������������) ������ ���", 20);
		cause
				.addRectif1("3.5.1 � �������������� ������", 63)
				.addRectif1("3.5.2 � ������ ��������� ������", 64);
		this.causes.put(cause.getId(), cause);
		cause = new CauseL("3.6 ������", 21);
		cause
				.addRectif1("3.6.1 �� ����������� � ����� ���", 65);
		this.causes.put(cause.getId(), cause);
		cause = new CauseL("4.1 �� ����������� �������� ���", 22);
		cause
				.addRectif1("4.1.1 �� ����������� �������� ��� ����������� �������, ��������", 66)
				.addRectif1("������� ��", 67);
		this.causes.put(cause.getId(), cause);
		cause = new CauseL("4.12 � �������� �������� ������� �� ����������� ������ �� ���������� ��C", 33);
		cause
				.addRectif1("-", 69);
		this.causes.put(cause.getId(), cause);

		Rectif1L rectif1 = new Rectif1L("����� � ������ ������ ���", 1);
		addRectif2Smo(rectif1);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("����������������� ������ ������ ���(����� ������ ��������)", 2);
		addRectif2Smo(rectif1);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("������", 3);
		rectif1
			.addRectif2("-", 4);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("��� ���������� �����������", 4);
		addRectif2Denied(rectif1);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("�� ���������� �����������", 5);
		addRectif2Denied(rectif1);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("�����������", 12);
		rectif1
			.addRectif2("������� ������� �������� � ������������", 7)
			.addRectif2("������� ������� �������� � ������", 8)
			.addRectif2("���������� �������� ��� ������ �����-���������", 9)
			.addRectif2("���������� �������� ��� ������ ������-������������", 10)
			.addRectif2("����� � ������ ����������� � ������ �� ��� ���������� ������������ �����-�����������", 11)
			.addRectif2("���������� �������� ��� ���������� ���������������, ���������������� � ������������ ������������", 12)
			.addRectif2("����� � ������ ����������� � ������ �� ��� ���������� ����������� ���������� ����������� ���������������, ���������������� � ������������ ������������", 13)
			.addRectif2("�� ������������� ������ ������ ��", 14)
			.addRectif2("���������������� ������������ ���������", 15)
			.addRectif2("������", 16);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("���������", 11);
		rectif1
			.addRectif2("����� � ����������� ���������� ������������ ����� �����������", 17)
			.addRectif2("����� � ����������� ���������� ���������������, ���������������� � ������������ ������������", 18)
			.addRectif2("���������� �������� ��� �������� ��������������", 19)
			.addRectif2("���������� �������� ���������� ���������� � �������� ����� ��� ��������������", 20)
			.addRectif2("���������������� ������������ ���������", 21)
			.addRectif2("������", 22);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("13.1 �� ���������� �����������", 55);
		addRectif2Pay(rectif1);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("13.2 ��� ���������� �����������", 56);
		addRectif2Pay(rectif1);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("�� ���������� ����������� (15.1)", 58);
		addRectif2Hospital(rectif1);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("��� ���������� ����������� (15.2)", 59);
		addRectif2Hospital(rectif1);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("������-���������", 13);
		addRectif2Act(rectif1);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("����������", 14);
		addRectif2Act(rectif1);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("������������-������������", 15);
		addRectif2Act(rectif1);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("���� ����� ��������� ��������", 16);
		addRectif2Act(rectif1);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("���������������", 17);
		addRectif2Act(rectif1);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("���������", 18);
		addRectif2Act(rectif1);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("�������", 19);
		addRectif2Act(rectif1);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("����������������", 20);
		addRectif2Act(rectif1);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("������� ���������", 21);
		addRectif2Act(rectif1);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("������� �������", 22);
		addRectif2Act(rectif1);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("������� ����������", 23);
		addRectif2Act(rectif1);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("������� ������-��������", 24);
		addRectif2Act(rectif1);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("������� ������", 25);
		addRectif2Act(rectif1);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("������� ������������", 26);
		addRectif2Act(rectif1);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("����������", 27);
		addRectif2Act(rectif1);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("������������", 28);
		addRectif2Act(rectif1);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("���������", 29);
		addRectif2Act(rectif1);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("��������", 30);
		addRectif2Act(rectif1);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("�����������", 31);
		addRectif2Act(rectif1);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("����������", 32);
		addRectif2Act(rectif1);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("��������", 33);
		addRectif2Act(rectif1);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("�������", 34);
		addRectif2Act(rectif1);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("�����������������", 35);
		addRectif2Act(rectif1);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("�����������", 36);
		addRectif2Act(rectif1);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("�������", 37);
		addRectif2Act(rectif1);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("�����������", 38);
		addRectif2Act(rectif1);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("����������", 39);
		addRectif2Act(rectif1);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("��������-���������� ������", 40);
		addRectif2Act(rectif1);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("����������", 41);
		addRectif2Act(rectif1);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("��������-�������������", 42);
		addRectif2Act(rectif1);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("��������", 43);
		addRectif2Act(rectif1);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("����������", 44);
		addRectif2Act(rectif1);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("����������� ������", 45);
		addRectif2Act(rectif1);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("�����������-�������", 46);
		addRectif2Act(rectif1);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("������", 47);
		addRectif2Act(rectif1);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("������", 48);
		addRectif2Act(rectif1);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("��������-������� ������", 49);
		addRectif2Act(rectif1);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("������������", 50);
		addRectif2Act(rectif1);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("���� ���", 70);
		addRectif2Act(rectif1);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("���� �������������� �����������", 71);
		addRectif2Act(rectif1);
		this.rectifs1.put(rectif1.getId(), rectif1);
		rectif1 = new Rectif1L("�����������", 72);
		addRectif2Act(rectif1);
		
		Rectif2L rectif2 = new Rectif2L("������� ������� �������� � ������������", 7);
		rectif2
			.addRectif3("10-20 �����", 1)
			.addRectif3("20-30 �����", 2)
			.addRectif3("����� 30 �����", 3);
		this.rectifs2.put(rectif2.getId(), rectif2);
		rectif2 = new Rectif2L("������� ������� �������� � ������", 8);
		rectif2
			.addRectif3("15-30 �����", 4)
			.addRectif3("30 ����� � 1 ���", 5)
			.addRectif3("����� 1 ����", 6);
		this.rectifs2.put(rectif2.getId(), rectif2);
		rectif2 = new Rectif2L("���������� �������� ��� ������ �����-���������", 9);
		rectif2
			.addRectif3("3-5 ����", 7)
			.addRectif3("5-7 ����", 8)
			.addRectif3("����� 7 ����", 9);
		this.rectifs2.put(rectif2.getId(), rectif2);
		rectif2 = new Rectif2L("���������� �������� ��� ������ ������-������������", 10);
		rectif2
			.addRectif3("7-10 ����", 10)
			.addRectif3("10-14 ����", 11)
			.addRectif3("����� 14 ����", 12);
		this.rectifs2.put(rectif2.getId(), rectif2);
		rectif2 = new Rectif2L("����� � ������ ����������� � ������ �� ��� ���������� ������������ �����-�����������", 11);
		addRectif3Doctor(rectif2);
		this.rectifs2.put(rectif2.getId(), rectif2);
		rectif2 = new Rectif2L("���������� �������� ��� ���������� ���������������, ���������������� � ������������ ������������", 12);
		rectif2
			.addRectif3("14-15 ����", 51)
			.addRectif3("15-20 ����", 52)
			.addRectif3("����� 20 ����", 53);
		this.rectifs2.put(rectif2.getId(), rectif2);
		rectif2 = new Rectif2L("����� � ������ ����������� � ������ �� ��� ���������� ����������� ���������� ����������� ���������������, ���������������� � ������������ ������������", 13);
		rectif2
			.addRectif3("���", 54)
			.addRectif3("��������������", 55)
			.addRectif3("���", 56)
			.addRectif3("��������������� ������������", 57)
			.addRectif3("�������-������������ ������������", 58)
			.addRectif3("������", 59);
		this.rectifs2.put(rectif2.getId(), rectif2);
		rectif2 = new Rectif2L("�� ������������� ������ ������ ��", 14);
		rectif2
			.addRectif3("����� ������ ������������", 60)
			.addRectif3("����� ���������, ��������", 61)
			.addRectif3("���������� ��������������� ������������", 62);
		this.rectifs2.put(rectif2.getId(), rectif2);
		rectif2 = new Rectif2L("����� � ����������� ���������� ������������ ����� �����������", 17);
		addRectif3Doctor(rectif2);
		this.rectifs2.put(rectif2.getId(), rectif2);
		rectif2 = new Rectif2L("����� � ����������� ���������� ���������������, ���������������� � ������������ ������������", 18);
		rectif2
			.addRectif3("���", 63)
			.addRectif3("��������������", 64)
			.addRectif3("���", 65)
			.addRectif3("��", 66)
			.addRectif3("���", 67)
			.addRectif3("�������-������������ ������������", 68)
			.addRectif3("������", 69);
		this.rectifs2.put(rectif2.getId(), rectif2);
		rectif2 = new Rectif2L("���������� �������� ��� �������� ��������������", 19);
		rectif2
			.addRectif3("������� ���������", 70)
			.addRectif3("���������", 71);
		this.rectifs2.put(rectif2.getId(), rectif2);
		rectif2 = new Rectif2L("���������� �������� ���������� ���������� � �������� ����� ��� ��������������", 20);
		rectif2
			.addRectif3("�������� ����������� ������", 72)
			.addRectif3("����������, ���������� ����������� ������", 73);
		this.rectifs2.put(rectif2.getId(), rectif2);
		rectif2 = new Rectif2L("���������� ������ �� ����������� ������", 24);
		rectif2
			.addRectif3("�����������", 74)
			.addRectif3("���������", 75);
		this.rectifs2.put(rectif2.getId(), rectif2);
		rectif2 = new Rectif2L("�����������", 26);
		rectif2
			.addRectif3("�� ����������� ������ �������������", 76)
			.addRectif3("�� ����������� ������ ������������ ����� �����������", 77)
			.addRectif3("�� ����������� ������ ������������", 78)
			.addRectif3("�� ����������� ������ ������", 79)
			.addRectif3("�� ������������ ������������ ��������������� ������������", 80)
			.addRectif3("�� ������������ ������������ ������������� ������������", 81)
			.addRectif3("�� ������������ ������������ ������������ ������������", 82)
			.addRectif3("�� ������������ ������������ ������", 83)
			.addRectif3("�� ������ �������������� � ���������������� ������������ ���", 84)
			.addRectif3("�� ������ �������������� � ���������������� ������������ ���", 85)
			.addRectif3("�� ������ �������������� � ���������������� ������������ ��������������", 86)
			.addRectif3("�� ������ �������������� � ���������������� ������������ ��", 87)
			.addRectif3("�� ������ �������������� � ���������������� ������������ ���", 88)
			.addRectif3("�� ������ �������������� � ���������������� ������������ ������", 89)
			.addRectif3("�� ��������� ��������� ��� ���������� �������-��������������� �����������", 90);
		this.rectifs2.put(rectif2.getId(), rectif2);
		rectif2 = new Rectif2L("���������", 27);
		rectif2
			.addRectif3("�� ����������� ������ ����������� �������", 91)
			.addRectif3("�� ����������� ������ �������������", 92)
			.addRectif3("�� ����������� ������ ������������ ����� �����������", 93)
			.addRectif3("�� ����������� ������ ������������", 94)
			.addRectif3("�� ����������� ������ ������", 95)
			.addRectif3("�� ������������ ������������ ��������������� ������������", 96)
			.addRectif3("�� ������������ ������������ ������������� ������������", 97)
			.addRectif3("�� ������������ ������������ ������������ ������������", 98)
			.addRectif3("�� ������������ ������������ ������", 99)
			.addRectif3("�� ������ �������������� � ���������������� ������������ ���", 100)
			.addRectif3("�� ������ �������������� � ���������������� ������������ ���", 101)
			.addRectif3("�� ������ �������������� � ���������������� ������������ ��������������", 102)
			.addRectif3("�� ������ �������������� � ���������������� ������������ ��������������� ������������", 103)
			.addRectif3("�� ������ �������������� � ���������������� ������������ ��", 104)
			.addRectif3("�� ������ �������������� � ���������������� ������������ ���", 105)
			.addRectif3("�� ������ �������������� � ���������������� ������������ ������", 106)
			.addRectif3("�� ������������ ������������� ������� � ������ ������������� �������", 107)
			.addRectif3("�� ������������ ��������� ���������� � ������ ������������� �������", 108)
			.addRectif3("�� ��� ��������� ����������� ������", 109);		
		this.rectifs2.put(rectif2.getId(), rectif2);
		rectif2 = new Rectif2L("������������, ��������������� ��� ������������ ���������� (��� ����������� ������) ����������� �������� ��������������� � (���) �������� �����������, ����������� ������������ � ������������ � �������� �������� ����������� ������ � (���) ����������� ����������� ������:", 29);
		rectif2
			.addRectif3("�� ���������� �� ��������� �������� ��������������� ����", 110)
			.addRectif3("��������� � ��������� ������ ������� ����� ������������� (�� ����������� ������� ������ ��������������� ����, ������������ � ������������� �������)", 111)
			.addRectif3("��������� � ��������� ��������� �������� ��������������� ����, ���� ��������� ���� ���������������� ���������� �����������, ���� ��������� ���� ������������� ������ ����������� (�� ����������� ������� ������ ��������������� ���� �� �������, ������������ � ������������� �������)", 112)
			.addRectif3("��������� � ������������� (�� ����������� ������� ������ ��������������� ���� �� �������, ������������ � ������������� �������)", 113)
			.addRectif3("��������� � ���������� ������ (�� ����������� ������� ������ ��������������� ���� �� �������, ������������ � ������������� �������)", 114);
		this.rectifs2.put(rectif2.getId(), rectif2);
		rectif2 = new Rectif2L("���������� ������������, ������������� � ����������� ����� ������ � �� ������������������ ����������� ����������� ������ �����������:", 30);
		rectif2
			.addRectif3("��������� � ��������� ������ �������, ���������� ��������� ������� ��� ���������� ������������� ����������� ��� ��������� �������� ��������������� ����", 115)
			.addRectif3("��������� � ��������� ��������� �������� ��������������� ����, ���� ��������� ���� ���������������� ���������� �����������, ���� ��������� ���� ������������� ������ ����������� (�� ����������� ������� ������ ��������������� ���� �� �������, ������������ � ������������� �������)", 116);
		this.rectifs2.put(rectif2.getId(), rectif2);

		Rectif3L rectif3 = new Rectif3L("7-10 ����", 10);
		addRectif4Doctor(rectif3);
		this.rectifs3.put(rectif3.getId(), rectif3);
		rectif3 = new Rectif3L("10-14 ����", 11);
		addRectif4Doctor(rectif3);
		this.rectifs3.put(rectif3.getId(), rectif3);
		rectif3 = new Rectif3L("����� 14 ����", 12);
		addRectif4Doctor(rectif3);
		this.rectifs3.put(rectif3.getId(), rectif3);
		rectif3 = new Rectif3L("14-15 ����", 51);
		addRectif4Service(rectif3);
		this.rectifs3.put(rectif3.getId(), rectif3);
		rectif3 = new Rectif3L("15-20 ����", 52);
		addRectif4Service(rectif3);
		this.rectifs3.put(rectif3.getId(), rectif3);
		rectif3 = new Rectif3L("����� 20 ����", 53);
		addRectif4Service(rectif3);
		this.rectifs3.put(rectif3.getId(), rectif3);
		rectif3 = new Rectif3L("������� ���������", 70);
		rectif3
			.addRectif4("20-30 ����", 57)
			.addRectif4("30-45 ����", 58)
			.addRectif4("����� 45 ����", 59);
		this.rectifs3.put(rectif3.getId(), rectif3);
		rectif3 = new Rectif3L("���������", 71);
		rectif3
			.addRectif4("1-2 ������", 60)
			.addRectif4("2-3 ������", 61)
			.addRectif4("����� 3 �������", 62);
		this.rectifs3.put(rectif3.getId(), rectif3);
		rectif3 = new Rectif3L("�����������", 74);
		rectif3
			.addRectif4("�� ����������� ������ �������������", 63)
			.addRectif4("�� ����������� ������ ������������ �����-�����������", 64)
			.addRectif4("�� ����������� ������ ������������", 65)
			.addRectif4("�� ����������� ������ ������", 66)
			.addRectif4("�� �������-������������ ������������ ��������������� ������������", 67)
			.addRectif4("�� �������-������������ ������������ ������������� ������������", 68)
			.addRectif4("�� �������-������������ ������������ ������������ ������������", 69)
			.addRectif4("�� �������-������������ ������������ ������", 70)
			.addRectif4("�� �������������� � ���������������� ������������ ���", 71)
			.addRectif4("�� �������������� � ���������������� ������������ ���", 72)
			.addRectif4("�� �������������� � ���������������� ������������ ��������������", 73)
			.addRectif4("�� �������������� � ���������������� ������������ ��", 74)
			.addRectif4("�� �������������� � ���������������� ������������ ���", 75)
			.addRectif4("�� �������������� � ���������������� ������������ ������", 76)
			.addRectif4("�� ��������� ��������� ��� ���������� �������-��������������� �����������", 77);			
		this.rectifs3.put(rectif3.getId(), rectif3);
		rectif3 = new Rectif3L("���������", 75);
		rectif3
			.addRectif4("�� ����������� ������ ����������� �������", 78)
			.addRectif4("�� ����������� ������ �������������", 79)
			.addRectif4("�� ����������� ������ ������������ ����� �����������", 80)
			.addRectif4("�� ����������� ������ ������������", 81)
			.addRectif4("�� ����������� ������ ������", 82)
			.addRectif4("�� ������������ ������������ ��������������� ������������", 83)
			.addRectif4("�� ������������ ������������ ������������� ������������", 84)
			.addRectif4("�� ������������ ������������ ������������ ������������", 85)
			.addRectif4("�� ������������ ������������ ������", 86)
			.addRectif4("�� �������������� � ���������������� ������������ ���", 87)
			.addRectif4("�� �������������� � ���������������� ������������ ���", 88)
			.addRectif4("�� �������������� � ���������������� ������������ ��������������", 89)
			.addRectif4("�� �������������� � ���������������� ������������ ��", 90)
			.addRectif4("�� �������������� � ���������������� ������������ ���", 91)
			.addRectif4("�� �������������� � ���������������� ������������ ������", 92)
			.addRectif4("�� ������������� �������� � ������ ������������� �������", 93)
			.addRectif4("�� ��������� ��������� � ������ ������������� �������", 94)
			.addRectif4("�� ��� ��������� ����������� ������", 95);			
		this.rectifs3.put(rectif3.getId(), rectif3);
		rectif3 = new Rectif3L("�� ����������� ������ �������������", 76);
		addRectif4Pay(rectif3);
		this.rectifs3.put(rectif3.getId(), rectif3);
		rectif3 = new Rectif3L("�� ����������� ������ ������������ ����� �����������", 77);
		addRectif4Pay(rectif3);
		this.rectifs3.put(rectif3.getId(), rectif3);
		rectif3 = new Rectif3L("�� ����������� ������ ������������", 78);
		addRectif4Pay(rectif3);
		this.rectifs3.put(rectif3.getId(), rectif3);
		rectif3 = new Rectif3L("�� ����������� ������ ������", 79);
		addRectif4Pay(rectif3);
		this.rectifs3.put(rectif3.getId(), rectif3);
		rectif3 = new Rectif3L("�� ������������ ������������ ��������������� ������������", 80);
		addRectif4Pay(rectif3);
		this.rectifs3.put(rectif3.getId(), rectif3);
		rectif3 = new Rectif3L("�� ������������ ������������ ������������� ������������", 81);
		addRectif4Pay(rectif3);
		this.rectifs3.put(rectif3.getId(), rectif3);
		rectif3 = new Rectif3L("�� ������������ ������������ ������������ ������������", 82);
		addRectif4Pay(rectif3);
		this.rectifs3.put(rectif3.getId(), rectif3);
		rectif3 = new Rectif3L("�� ������������ ������������ ������", 83);
		addRectif4Pay(rectif3);
		this.rectifs3.put(rectif3.getId(), rectif3);
		rectif3 = new Rectif3L("�� ������ �������������� � ���������������� ������������ ���", 84);
		addRectif4Pay(rectif3);
		this.rectifs3.put(rectif3.getId(), rectif3);
		rectif3 = new Rectif3L("�� ������ �������������� � ���������������� ������������ ���", 85);
		addRectif4Pay(rectif3);
		this.rectifs3.put(rectif3.getId(), rectif3);
		rectif3 = new Rectif3L("�� ������ �������������� � ���������������� ������������ ��������������", 86);
		addRectif4Pay(rectif3);
		this.rectifs3.put(rectif3.getId(), rectif3);
		rectif3 = new Rectif3L("�� ������ �������������� � ���������������� ������������ ��", 87);
		addRectif4Pay(rectif3);
		this.rectifs3.put(rectif3.getId(), rectif3);
		rectif3 = new Rectif3L("�� ������ �������������� � ���������������� ������������ ���", 88);
		addRectif4Pay(rectif3);
		this.rectifs3.put(rectif3.getId(), rectif3);
		rectif3 = new Rectif3L("�� ������ �������������� � ���������������� ������������ ������", 89);
		addRectif4Pay(rectif3);
		this.rectifs3.put(rectif3.getId(), rectif3);
		rectif3 = new Rectif3L("�� ��������� ��������� ��� ���������� �������-��������������� �����������", 90);
		addRectif4Pay(rectif3);
		this.rectifs3.put(rectif3.getId(), rectif3);
		rectif3 = new Rectif3L("�� ����������� ������ ����������� �������", 91);
		addRectif4Pay(rectif3);
		this.rectifs3.put(rectif3.getId(), rectif3);
		rectif3 = new Rectif3L("�� ����������� ������ �������������", 92);
		addRectif4Pay(rectif3);
		this.rectifs3.put(rectif3.getId(), rectif3);
		rectif3 = new Rectif3L("�� ����������� ������ ������������ ����� �����������", 93);
		addRectif4Pay(rectif3);
		this.rectifs3.put(rectif3.getId(), rectif3);
		rectif3 = new Rectif3L("�� ����������� ������ ������������", 94);
		addRectif4Pay(rectif3);
		this.rectifs3.put(rectif3.getId(), rectif3);
		rectif3 = new Rectif3L("�� ����������� ������ ������", 95);
		addRectif4Pay(rectif3);
		this.rectifs3.put(rectif3.getId(), rectif3);
		rectif3 = new Rectif3L("�� ������������ ������������ ��������������� ������������", 96);
		addRectif4Pay(rectif3);
		this.rectifs3.put(rectif3.getId(), rectif3);
		rectif3 = new Rectif3L("�� ������������ ������������ ������������� ������������", 97);
		addRectif4Pay(rectif3);
		this.rectifs3.put(rectif3.getId(), rectif3);
		rectif3 = new Rectif3L("�� ������������ ������������ ������������ ������������", 98);
		addRectif4Pay(rectif3);
		this.rectifs3.put(rectif3.getId(), rectif3);
		rectif3 = new Rectif3L("�� ������������ ������������ ������", 99);
		addRectif4Pay(rectif3);
		this.rectifs3.put(rectif3.getId(), rectif3);
		rectif3 = new Rectif3L("�� ������ �������������� � ���������������� ������������ ���", 100);
		addRectif4Pay(rectif3);
		this.rectifs3.put(rectif3.getId(), rectif3);
		rectif3 = new Rectif3L("�� ������ �������������� � ���������������� ������������ ���", 101);
		addRectif4Pay(rectif3);
		this.rectifs3.put(rectif3.getId(), rectif3);
		rectif3 = new Rectif3L("�� ������ �������������� � ���������������� ������������ ��������������", 102);
		addRectif4Pay(rectif3);
		this.rectifs3.put(rectif3.getId(), rectif3);
		rectif3 = new Rectif3L("�� ������ �������������� � ���������������� ������������ ��������������� ������������", 103);
		addRectif4Pay(rectif3);
		this.rectifs3.put(rectif3.getId(), rectif3);
		rectif3 = new Rectif3L("�� ������ �������������� � ���������������� ������������ ��", 104);
		addRectif4Pay(rectif3);
		this.rectifs3.put(rectif3.getId(), rectif3);
		rectif3 = new Rectif3L("�� ������ �������������� � ���������������� ������������ ���", 105);
		addRectif4Pay(rectif3);
		this.rectifs3.put(rectif3.getId(), rectif3);
		rectif3 = new Rectif3L("�� ������ �������������� � ���������������� ������������ ������", 106);
		addRectif4Pay(rectif3);
		this.rectifs3.put(rectif3.getId(), rectif3);
		rectif3 = new Rectif3L("�� ������������ ������������� ������� � ������ ������������� �������", 107);
		addRectif4Pay(rectif3);
		this.rectifs3.put(rectif3.getId(), rectif3);
		rectif3 = new Rectif3L("�� ������������ ��������� ���������� � ������ ������������� �������", 108);
		addRectif4Pay(rectif3);
		this.rectifs3.put(rectif3.getId(), rectif3);
		rectif3 = new Rectif3L("�� ��� ��������� ����������� ������", 109);
		addRectif4Pay(rectif3);
		this.rectifs3.put(rectif3.getId(), rectif3);
	}

	private void addRectif4Pay(Rectif3L rectif3) {
		rectif3
			.addRectif4("�������� ������� ���", 96)
			.addRectif4("�������� � ����� ��", 97)
			.addRectif4("�������� ����� ����������� ���������", 98)
			.addRectif4("������", 99);
	}

	private void addRectif4Service(Rectif3L rectif3) {
		rectif3
			.addRectif4("���", 51)
			.addRectif4("��������������", 52)
			.addRectif4("���", 53)
			.addRectif4("��������������� ������������", 54)
			.addRectif4("�������-������������ ������������", 55)
			.addRectif4("������", 56);
	}

	private void addRectif4Doctor(Rectif3L rectif3) {
		rectif3
			.addRectif4("������-���������", 13)
			.addRectif4("����������", 14)
			.addRectif4("������������-������������", 15)
			.addRectif4("���� ����� ��������� ��������", 16)
			.addRectif4("���������������", 17)
			.addRectif4("���������", 18)
			.addRectif4("�������", 19)
			.addRectif4("����������������", 20)
			.addRectif4("������� ���������", 21)
			.addRectif4("������� �������", 22)
			.addRectif4("������� ����������", 23)
			.addRectif4("������� ������-��������", 24)
			.addRectif4("������� ������", 25)
			.addRectif4("������� ������������", 26)
			.addRectif4("����������", 27)
			.addRectif4("������������", 28)
			.addRectif4("���������", 29)
			.addRectif4("��������", 30)
			.addRectif4("�����������", 31)
			.addRectif4("����������", 32)
			.addRectif4("��������", 33)
			.addRectif4("�������", 34)
			.addRectif4("�����������������", 35)
			.addRectif4("�����������", 36)
			.addRectif4("�������", 37)
			.addRectif4("�����������", 38)
			.addRectif4("����������", 39)
			.addRectif4("��������-���������� ������", 40)
			.addRectif4("����������", 41)
			.addRectif4("��������-�������������", 42)
			.addRectif4("��������", 43)
			.addRectif4("����������", 44)
			.addRectif4("����������� ������", 45)
			.addRectif4("�����������-�������", 46)
			.addRectif4("������", 47)
			.addRectif4("������", 48)
			.addRectif4("��������-������� ������", 49)
			.addRectif4("������������", 50)
			.addRectif4("���� ���", 100)
			.addRectif4("���� �������������� �����������", 101)
			.addRectif4("�����������", 102);

	}

	private void addRectif3Doctor(Rectif2L rectif2) {
		rectif2
			.addRectif3("������-���������", 13)
			.addRectif3("����������", 14)
			.addRectif3("������������-������������", 15)
			.addRectif3("���� ����� ��������� ��������", 16)
			.addRectif3("���������������", 17)
			.addRectif3("���������", 18)
			.addRectif3("�������", 19)
			.addRectif3("����������������", 20)
			.addRectif3("������� ���������", 21)
			.addRectif3("������� �������", 22)
			.addRectif3("������� ����������", 23)
			.addRectif3("������� ������-��������", 24)
			.addRectif3("������� ������", 25)
			.addRectif3("������� ������������", 26)
			.addRectif3("����������", 27)
			.addRectif3("������������", 28)
			.addRectif3("���������", 29)
			.addRectif3("��������", 30)
			.addRectif3("�����������", 31)
			.addRectif3("����������", 32)
			.addRectif3("��������", 33)
			.addRectif3("�������", 34)
			.addRectif3("�����������������", 35)
			.addRectif3("�����������", 36)
			.addRectif3("�������", 37)
			.addRectif3("�����������", 38)
			.addRectif3("����������", 39)
			.addRectif3("��������-���������� ������", 40)
			.addRectif3("����������", 41)
			.addRectif3("��������-�������������", 42)
			.addRectif3("��������", 43)
			.addRectif3("����������", 44)
			.addRectif3("����������� ������", 45)
			.addRectif3("�����������-�������", 46)
			.addRectif3("������", 47)
			.addRectif3("������", 48)
			.addRectif3("��������-������� ������", 49)
			.addRectif3("������������", 50)
			.addRectif3("���� ���", 117)
			.addRectif3("���� �������������� �����������", 118)
			.addRectif3("�����������", 119);
	}

	private void addRectif2Act(Rectif1L rectif1) {
		rectif1
			.addRectif2("���������� � ������������� ������� ������ ��������� ��������� ����� � ����������� ����������� ����������� ����������� (��������������� �� ���������� �������������� ���)", 28)
			.addRectif2("������������, ��������������� ��� ������������ ���������� (��� ����������� ������) ����������� �������� ��������������� � (���) �������� �����������, ����������� ������������ � ������������ � �������� �������� ����������� ������ � (���) ����������� ����������� ������:", 29)
			.addRectif2("���������� ������������, ������������� � ����������� ����� ������ � �� ������������������ ����������� ����������� ������ �����������:", 30)
			.addRectif2("��������������� � ����������� ����� ������ ����������� ���������� �������� ����������� ��� ���������� ������������ ������� (����� ����������� � ������������� ������� ������� ������ �� �������)", 31)
			.addRectif2("��������� ������������ ��������� ��������������� ���� �� ����������� ������� �� ������ ���� �� ����������� � ������� 30 ���� �� ��� ���������� ������������� ������� � 90 ���� �� ��� ���������� ������� � ���������� ���������� ���������� ������������� �������� � ��������� ��������, �������������� ����������� ������� ��� �������� ����������� (�� ����������� ������� �������� �������, ����������� ����������� � ������� �� ��������� � ���������� (�������������) ����������� ����������).", 32)
			.addRectif2("��������� �� ���� ����������� ����������� ��������������� � ������� (� ��� ����� ��������������� ������� �������� � ����������� ����������� ����� �������� ������), ��������� � ��������� ������ ������� � (���) ��������� ��������� �������� ��������������� ����", 33)
			.addRectif2("�������������� ��������������� ���� ��� ����������� ��������� (�������������� ��������������), ����������� ������ �������� ����� ���� ������������� � ������������� ������ � �����������-��������������� ��������, � �������� �������� ����������.", 34)
			.addRectif2("�������������� ��������������� ����, ����������� ������ �������� ������ ���� ������� � ���������� ������� ������� (������������ ��������������), ����� ������� �������������� �� ���������� ����������", 35)
			.addRectif2("�������������� ��������� ������ ������� �� ���� ����������� �����������, � ����� ���������� ���������� ����������� �����, ���������, �����-����, �� ��������� � ����������� ���������������, �������� �����������, ����������� ������������ � ������ ���������� ����������� ������", 36)
			.addRectif2("��������� ��������� ����� ����� � ��� �� ������������� � ���� ���� ��� �������� ������������ ����������� ������, �� ����������� ���������� ��������� ��� ����������� ��������� � ��������������, ��������, ������������� � ������ ����������� ������������.", 37)
			.addRectif2("�������� ��� ����������� ������������ ���������, ������������ �������� ������ ����������� ��������������� ���� (�������� ����������� �����������)", 38)
			.addRectif2("�������������� ���������� ������������� �������; ������������� ���������� ������������� ������� � ���������, �������� ��� ������������ �� ������������������ �������� � �.�., ��������� � ������ ��� �������� �������� �/��� ���������� � ���������� �������", 39)
			.addRectif2("������������ �� ���� ����������� ����������� ������������� ���������������������� �������� � ������������ � ����������� �����������������.", 40)
			.addRectif2("������� ����������� ������������ � ���������������������� ��������� 2-3 ���������", 41);
	}

	private void addRectif2Hospital(Rectif1L rectif1) {
		rectif1
			.addRectif2("�����������", 26)
			.addRectif2("���������", 27);
	}

	private void addRectif2Pay(Rectif1L rectif1) {
		rectif1
			.addRectif2("������������������ ������ ���", 23)
			.addRectif2("���������� ������ �� ����������� ������", 24)
			.addRectif2("������", 25);
	}

	private void addRectif2Denied(Rectif1L rectif1) {
		rectif1
			.addRectif2("�����", 5)
			.addRectif2("������", 6);
	}

	private void addRectif2Smo(Rectif1L rectif1) {
		rectif1
			.addRectif2("��� ��� ������-��Ļ", 1)
			.addRectif2("������ ������������-��������� ��� ������-�ѻ", 2)
			.addRectif2("������ ��� ��� �����������-̻ � �.������������", 3);
	}
	
}
