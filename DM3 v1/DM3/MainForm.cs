/*
 * Создано в SharpDevelop.
 * Дата: 21.10.2019
 * 
 * Для изменения этого шаблона используйте меню "Инструменты | Параметры | Кодирование | Стандартные заголовки".
 */
using System;
using System.Collections.Generic;
using System.Drawing;
using System.Windows.Forms;

namespace DM3
{
	public partial class MainForm : Form
	{
		Graphics graph;
		Pen p =new Pen (Color.Red,5);
		Brush b = new SolidBrush (Color.Black);
		int [,]mass=new int[15,2];
		int koor1, koor2, koor3, koor4;
		int p1;
		int p2;
		int s;
		
		public MainForm()
		{
			//
			// The InitializeComponent() call is required for Windows Forms designer support.
			//
			InitializeComponent();
			graph=Graphics.FromHwnd(pictureBox1.Handle);
			
		}
		void Button1Click(object sender, EventArgs e)
		{
			graph.FillEllipse (b,138,10,8,8);
		//graph.DrawBezier (p, 30, 10, 20, 45, 30, 44, 30, 26);
			mass[0,0]=138; mass[0,1]=10;
			graph.DrawString("0", new Font ("Arial", 8), new SolidBrush (Color.Black), 138,17);
			
			
			graph.FillEllipse (b,247,82,8,8);
			mass[1,0]=247; mass[1,1]=82;
			graph.DrawString("1", new Font ("Arial", 8), new SolidBrush (Color.Black), 247,89);
			
			
			graph.FillEllipse (b,190,210,8,8);
			mass[2,0]=190; mass[2,1]=210;
			graph.DrawString("2", new Font ("Arial", 8), new SolidBrush (Color.Black), 190,217);
			
			graph.FillEllipse (b,82,210,8,8);
			mass[3,0]=82; mass[3,1]=210;
			graph.DrawString("3", new Font ("Arial", 8), new SolidBrush (Color.Black), 82,219);
			
			graph.FillEllipse (b,29,82,8,8);
			mass[4,0]=29; mass[4,1]=82;
			graph.DrawString("4", new Font ("Arial", 8), new SolidBrush (Color.Black), 29,90);
			
			
			dataGridView1.ColumnCount = 5;
			dataGridView1.RowCount =Convert.ToInt32(tz.Text);
			dataGridView1.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
            dataGridView1.RowHeadersWidth = 60;
            for (int i=0; i<5; i++)
            	dataGridView1.Columns[i].HeaderText="В"+i.ToString();
            for(int j=0;j< dataGridView1.RowCount;j++)
                dataGridView1.Rows[j].HeaderCell.Value="Р"+j.ToString();
            if (Convert.ToInt32(tz.Text) >=5) button2.Enabled=true;
            else button2.Enabled=false;
		}
		void Button2Click(object sender, EventArgs e)
		{
			
			dataGridView1.Rows[0].Cells[0].Value ="1";
			dataGridView1.Rows[0].Cells[1].Value ="1";
			dataGridView1.Rows[1].Cells[1].Value ="1";
			dataGridView1.Rows[1].Cells[3].Value ="1";
            dataGridView1.Rows[2].Cells[0].Value = "1";
            dataGridView1.Rows[2].Cells[3].Value = "1";
            dataGridView1.Rows[3].Cells[0].Value = "1";
            dataGridView1.Rows[3].Cells[4].Value = "1";
            dataGridView1.Rows[4].Cells[1].Value = "1";
            dataGridView1.Rows[4].Cells[2].Value = "1";

        }
		void Button3Click(object sender, EventArgs e)
		{
			dataGridView2.ColumnCount = 5;
			dataGridView2.RowCount =5;
			dataGridView2.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
            dataGridView2.RowHeadersWidth = 60;
            for (int i=0; i<5; i++) {
            	dataGridView2.Columns[i].HeaderText="В"+i.ToString();
            	dataGridView2.Rows[i].HeaderCell.Value="В"+i.ToString();
            	dataGridView2.Rows[i].Cells[i].Style.BackColor = Color.Aquamarine;
            }
		for(int j=0;j< dataGridView1.RowCount;j++)
		{
			s=0; koor1=-1; koor2=-1; koor3=-1; koor4=-1; p1=-1; p2=-2;
			for (int i=0; i<5; i++) {
				s=s+Convert.ToInt32(dataGridView1.Rows[j].Cells[i].Value);
				if ((koor1==-1) && (Convert.ToInt32(dataGridView1.Rows[j].Cells[i].Value)==1))
				    {
				    	koor1=mass[i,0];
				    	koor2=mass[i,1];
				    	p1=i;
				    }
				if ((koor1!=-1) && (Convert.ToInt32(dataGridView1.Rows[j].Cells[i].Value)==1))
				    {
				    	koor3=mass[i,0];
				    	koor4=mass[i,1];
				    	p2=i;
				    }
	}
			if ((s!=2) && (s!=0))
				            {
				            	MessageBox.Show ("Матрица некорректна");
				            	break;
				            }
			    else if (koor1!=-1 && koor2!=-1 && koor3!=-1 && koor4!=-1)
			    {
			    	graph.DrawLine (p,koor1, koor2, koor3, koor4);
			    	dataGridView2.Rows[p1].Cells[p2].Value="1";
            		dataGridView2.Rows[p2].Cells[p1].Value="1";
			    }
				    	
		}
				
  }
      
		void Button4Click(object sender, EventArgs e)
		{
			pictureBox1.Image = null;
			dataGridView1.Rows.Clear();
			dataGridView2.Rows.Clear();
		}
                	
	}}
		
	